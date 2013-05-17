package tman.system.peer.tman;

import se.sics.kompics.address.Address;
import search.system.peer.partition.PartitionRange;

/**
 *
 * @author mattija
 */
public class TManPeer {
    public final Address address;
    public final PartitionRange partitionRange;

    public TManPeer(Address address, PartitionRange partitionRange) {
        this.address = address;
        this.partitionRange = partitionRange;
    }
}
