package search.system.peer;

import se.sics.kompics.address.Address;
import search.system.peer.partition.PartitionRange;

/**
 *
 * @author mattija
 */
public class SearchPeerInfo {
    private PartitionRange partitionRange;
    private Address address;

    public SearchPeerInfo(PartitionRange partitionRange, Address address) {
        this.partitionRange = partitionRange;
        this.address = address;
    }

    public PartitionRange getPartitionRange() {
        return partitionRange;
    }

    public Address getAddress() {
        return address;
    }
}
