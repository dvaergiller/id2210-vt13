/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.system.peer.search;

import java.util.UUID;
import se.sics.kompics.Event;
import se.sics.kompics.address.Address;
import se.sics.kompics.network.Message;

/**
 *
 * @author mattija
 */
public class AddIndexEntry {
    public static class Request extends Message {
        private final UUID requestId;
        private final IndexEntry entry;
        
        public Request(UUID requestId, Address source, Address destination, IndexEntry entry) {
            super(source, destination);
            this.requestId = requestId;
            this.entry = entry;
        }

        public UUID getRequestId() {
            return requestId;
        }

        public IndexEntry getEntry() {
            return entry;
        }
    }
    
    public static class Success extends Message {
        private final UUID requestId;
        
        public Success(UUID requestId, Address source, Address destination) {
            super(source, destination);
            this.requestId = requestId;
        }

        public UUID getRequestId() {
            return requestId;
        }
    }
    
    public static class Timeout extends Event {
        private final UUID requestId;

        public Timeout(UUID requestId) {
            this.requestId = requestId;
        }

        public UUID getRequestId() {
            return requestId;
        }
    }
}
