package search.system.peer.publish;

import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;
import se.sics.kompics.ComponentDefinition;
import se.sics.kompics.Handler;
import se.sics.kompics.Negative;
import se.sics.kompics.Positive;
import se.sics.kompics.address.Address;
import se.sics.kompics.network.Message;
import se.sics.kompics.network.Network;
import search.system.peer.search.IndexEntry;
import tman.system.peer.tman.TManPort;
import tman.system.peer.tman.TManSample;

/**
 *
 * @author mattija
 */
public final class Publish extends ComponentDefinition {
    private Positive<Network> networkPort = requires(Network.class);
    private Positive<TManPort> tmanPort = requires(TManPort.class);
    private Negative<PublishPort> publishPort = provides(PublishPort.class);

    private Address self;
    
    private LinkedList<IndexEntry> entryQueue = new LinkedList<IndexEntry>();
    private Collection<Address> tmanPartners;
    
    private int highestIndexId = 0;
    private boolean busy = false;
    private UUID instanceId;
    private int numStatesReceived;
    private int numAcksReceived;
    
    public Publish() {
        subscribe(handleInit, control);
        subscribe(handleAssignRequest, publishPort);
        subscribe(handleCheckState, networkPort);
        subscribe(handlePublish, networkPort);
        subscribe(handleState, networkPort);
        subscribe(handleAck, networkPort);
        subscribe(handleTManSample, tmanPort);
    }
    
    private void broadcast(Message msg) {
        for(Address a : tmanPartners) {
            msg.setDestination(a);
            trigger(msg, networkPort);
        }
    }
    
    Handler<PublishInit> handleInit = new Handler<PublishInit>() {
        @Override
        public void handle(PublishInit event) {
            self = event.getSelf();
        }
    };
    
    Handler<PublishEvent.Request> handleAssignRequest = new Handler<PublishEvent.Request>() {
        @Override
        public void handle(PublishEvent.Request event) {
            
            entryQueue.addLast(event.getIndexEntry());
            tryStartPublishInstance();
        }
    };
    
    private void tryStartPublishInstance() {
        
        synchronized (this) {
            if(busy || entryQueue.isEmpty()) {
                return;
            }
            
            busy = true;
            numStatesReceived = 0;
            numAcksReceived = 0;
            instanceId = UUID.randomUUID();
            PublishMsg.CheckState msg = new PublishMsg.CheckState(instanceId, self, null);
            broadcast(msg);
        }
    }
    
    private void publishInstanceDone(UUID id) {
        synchronized (this) {
            if(id != instanceId) {
                return;
            }
            instanceId = null;
            entryQueue.pop();
            busy = false;
        }
        tryStartPublishInstance();
    }
    
    private void publishInstanceFailed(UUID id) {
        synchronized (this) {
            if(id != instanceId) {
                return;
            }
            instanceId = null;
            PublishEvent.Failure failMsg = new PublishEvent.Failure(entryQueue);
            entryQueue = new LinkedList<IndexEntry>();
            trigger(failMsg, publishPort);
            busy = false;
        }
    }
    
    /********** Reader events ************/
    
    Handler<PublishMsg.CheckState> handleCheckState = new Handler<PublishMsg.CheckState>() {
        @Override
        public void handle(PublishMsg.CheckState event) {
            int lowestPeer = Integer.MAX_VALUE;
            for(Address a : tmanPartners) {
                if(a.getId() < lowestPeer) {
                    lowestPeer = a.getId();
                }
            }
            
            PublishMsg.State msg = new PublishMsg.State(event.getInstanceId(), highestIndexId, 
                    lowestPeer, self, event.getSource());
            trigger(msg, networkPort);
        }
    };
    
    Handler<PublishMsg.Publish> handlePublish = new Handler<PublishMsg.Publish>() {
        @Override
        public void handle(PublishMsg.Publish event) {
            PublishMsg.Ack ackMsg = new PublishMsg.Ack(event.getInstanceId(), self, event.getSource());
            trigger(ackMsg, networkPort);
            
            if(event.getIndexEntry().getIndexId() > highestIndexId) {
                highestIndexId = event.getIndexEntry().getIndexId();
            }
            
            PublishEvent.Notify notifyEvent = new PublishEvent.Notify(event.getIndexEntry());
            trigger(notifyEvent, publishPort);
        }
    };
    
    /********** Writer events ************/
    
    Handler<PublishMsg.State> handleState = new Handler<PublishMsg.State>() {
        @Override
        public synchronized void handle(PublishMsg.State event) {
            if(event.getInstanceId() != instanceId || numStatesReceived > tmanPartners.size() / 2) {
                return;
            }
            
            if(event.getLowestPeer() < self.getId()) {
                publishInstanceFailed(instanceId);
                return;
            }
            
            if(event.getHighiestIndex() > highestIndexId) {
                highestIndexId = event.getHighiestIndex();
            }
            
            numStatesReceived++;
            if(numStatesReceived > tmanPartners.size() / 2) {
                PublishMsg.Publish publishMsg = 
                        new PublishMsg.Publish(instanceId, entryQueue.getFirst(), self, null);
                broadcast(publishMsg);
            }
        }
    };
    
    Handler<PublishMsg.Ack> handleAck = new Handler<PublishMsg.Ack>() {
        @Override
        public void handle(PublishMsg.Ack event) {
            if(event.getInstanceId() != instanceId) {
                return;
            }
            
            numAcksReceived++;
            if(numAcksReceived > tmanPartners.size() / 2) {
                publishInstanceDone(instanceId);
            }
        }
    };
    
    /********** TMan ************/
    
    Handler<TManSample> handleTManSample = new Handler<TManSample>() {
        @Override
        public void handle(TManSample event) {
            tmanPartners = event.getSample();
        }
    };
}
