/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.system.peer.search;

import java.util.List;
import se.sics.kompics.address.Address;
import se.sics.kompics.network.Message;

/**
 *
 * @author mattija
 */
public class LeaderLookup {
    public static class Request extends Message {
        public Request(Address source, Address destination) {
            super(source, destination);
        }
    }
    
    public static class Response extends Message {
        private List<Address> suggestions;
        public Response(Address source, Address destination, List<Address> suggestions) {
            super(source, destination);
            this.suggestions = suggestions;
        }

        public List<Address> getSuggestions() {
            return suggestions;
        }
    }
    
    public static class Found extends Message {
        public Found(Address source, Address destination) {
            super(source, destination);
        }
    }
}
