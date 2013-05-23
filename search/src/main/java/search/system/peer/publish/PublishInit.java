package search.system.peer.publish;

import se.sics.kompics.Init;
import se.sics.kompics.address.Address;

/**
 *
 * @author mattija
 */
public class PublishInit extends Init {
    private final Address self;

    public PublishInit(Address self) {
        this.self = self;
    }

    public Address getSelf() {
        return self;
    }
}
