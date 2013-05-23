/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.system.peer.publish;

import se.sics.kompics.PortType;

/**
 *
 * @author mattija
 */
public class PublishPort extends PortType {{
    request(PublishEvent.Request.class);
    indication(PublishEvent.Failure.class);
    indication(PublishEvent.Notify.class);
}}
