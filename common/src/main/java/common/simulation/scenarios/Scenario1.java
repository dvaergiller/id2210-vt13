package common.simulation.scenarios;

import se.sics.kompics.p2p.experiment.dsl.SimulationScenario;

@SuppressWarnings("serial")
public class Scenario1 extends Scenario {
	private static SimulationScenario scenario = new SimulationScenario() {{
		StochasticProcess process1 = new StochasticProcess() {{
			eventInterArrivalTime(constant(100));
			raise(20, Operations.peerJoin(5), 
                                uniform(0, Integer.MAX_VALUE)
                             );
		}};
		
		StochasticProcess process2 = new StochasticProcess() {{
			eventInterArrivalTime(constant(100));
			raise(5, Operations.peerJoin(5), 
                                uniform(0, Integer.MAX_VALUE)
                                );
		}};
		
		StochasticProcess process3 = new StochasticProcess() {{
			eventInterArrivalTime(constant(100));
			raise(100, Operations.addIndexEntry(), 
                                uniform(0, Integer.MAX_VALUE)
                                );
		}};

		process1.start();
		process2.startAfterTerminationOf(2000, process1);
		process3.startAfterTerminationOf(2000, process2);
	}};

	// -------------------------------------------------------------------
	public Scenario1() {
		super(scenario);
	}
}
