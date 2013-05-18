/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tman.system.peer.tman;

import se.sics.kompics.Event;
import se.sics.kompics.address.Address;

/**
 *
 * @author mattija
 */
public class TManEvict extends Event {
    private final Address peer;

    public TManEvict(Address peer) {
        this.peer = peer;
    }

    public Address getPeer() {
        return peer;
    }
}
