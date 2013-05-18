package tman.system.peer.tman;

import se.sics.kompics.PortType;

public final class TManPort extends PortType {{
	positive(TManSample.class);
        negative(TManEvict.class);
}}
