/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.simulation.scenarios;

import se.sics.kompics.p2p.experiment.dsl.SimulationScenario;

@SuppressWarnings("serial")
public class Scenario3 extends Scenario {
	private static SimulationScenario scenario = new SimulationScenario() {{
                
		SimulationScenario.StochasticProcess process0 = new SimulationScenario.StochasticProcess() {{
			eventInterArrivalTime(constant(20));
			raise(2000, Operations.peerJoin(), 
                                uniform(0, Integer.MAX_VALUE)
                             );
		}};
		/*
		SimulationScenario.StochasticProcess process1 = new SimulationScenario.StochasticProcess() {{
			eventInterArrivalTime(constant(100));
			raise(100, Operations.addIndexEntry(), 
                                uniform(0, Integer.MAX_VALUE)
                                );
		}};
                */
		process0.start();
		//process1.startAfterTerminationOf(2000, process0);
	}};

	// -------------------------------------------------------------------
	public Scenario3() {
		super(scenario);
	}
}
