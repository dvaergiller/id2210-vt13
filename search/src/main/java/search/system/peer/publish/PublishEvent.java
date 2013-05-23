package search.system.peer.publish;

import java.util.Collection;
import se.sics.kompics.Event;
import se.sics.kompics.address.Address;
import search.system.peer.search.IndexEntry;

/**
 *
 * @author mattija
 */
public class PublishEvent {

    public static class Request extends Event {

        private final IndexEntry indexEntry;
        
        public Request(IndexEntry indexEntry) {
            this.indexEntry = indexEntry;
        }

        public IndexEntry getIndexEntry() {
            return indexEntry;
        }
    }

    public static class Failure extends Event {
        private final Collection<IndexEntry> pendingEntries;

        public Failure(Collection<IndexEntry> pendingEntries) {
            this.pendingEntries = pendingEntries;
        }

        public Collection<IndexEntry> getPendingEntries() {
            return pendingEntries;
        }
    }
    
    public static class Notify extends Event {

        private final IndexEntry indexEntry;

        public Notify(IndexEntry indexEntry) {
            this.indexEntry = indexEntry;
        }

        public IndexEntry getIndexEntry() {
            return indexEntry;
        }
    }
}
