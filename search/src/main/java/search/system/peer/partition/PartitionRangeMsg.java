package search.system.peer.partition;

import java.util.UUID;
import se.sics.kompics.address.Address;
import se.sics.kompics.network.Message;
import se.sics.kompics.timer.ScheduleTimeout;
import se.sics.kompics.timer.Timeout;

/**
 *
 * @author mattija
 */
public class PartitionRangeMsg {

    public class Request extends Message {
        public final UUID requestId;
        
        public Request(UUID requestId, Address source, Address dest) {
            super(source, dest);
            this.requestId = requestId;
        }
    }
    
    public class Response extends Message {
        public final UUID requestId;
        public final PartitionRange partitionRange;
        
        
        public Response(UUID requestId, Address source, Address dest, PartitionRange partitionRange) {
            super(source, dest);
            this.requestId = requestId;
            this.partitionRange = partitionRange;
        }
    }
    
    public class RequestTimeout extends Timeout {
        public final Address dest;
        
        public RequestTimeout(ScheduleTimeout timeout, Address dest) {
            super(timeout);
            this.dest = dest;
        }
    }
}
