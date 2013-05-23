/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.system.peer.publish;

import java.util.UUID;
import se.sics.kompics.address.Address;
import se.sics.kompics.network.Message;
import search.system.peer.search.IndexEntry;

/**
 *
 * @author mattija
 */
public class PublishMsg {
    
    public static class CheckState extends Message {
        private final UUID instanceId;
        
        public CheckState(UUID instanceId, Address source, Address destination) {
            super(source, destination);
            this.instanceId = instanceId;
        }

        public UUID getInstanceId() {
            return instanceId;
        }
    }
    
    public static class State extends Message {
        private final UUID instanceId;
        private final int highiestIndex;
        private final int lowestPeer;

        public State(UUID instanceId, int highiestIndex, int lowestPeer, Address source, Address destination) {
            super(source, destination);
            this.instanceId = instanceId;
            this.highiestIndex = highiestIndex;
            this.lowestPeer = lowestPeer;
        }

        public UUID getInstanceId() {
            return instanceId;
        }

        public int getHighiestIndex() {
            return highiestIndex;
        }

        public int getLowestPeer() {
            return lowestPeer;
        }
    }
    
    public static class Publish extends Message {
        private final UUID instanceId;
        private final IndexEntry indexEntry;

        public Publish(UUID instanceId, IndexEntry indexEntry, Address source, Address destination) {
            super(source, destination);
            this.instanceId = instanceId;
            this.indexEntry = indexEntry;
        }

        public UUID getInstanceId() {
            return instanceId;
        }

        public IndexEntry getIndexEntry() {
            return indexEntry;
        }
    }
    
    public static class Ack extends Message {
        private final UUID instanceId;

        public Ack(UUID instanceId, Address source, Address destination) {
            super(source, destination);
            this.instanceId = instanceId;
        }

        public UUID getInstanceId() {
            return instanceId;
        }
    }
}
