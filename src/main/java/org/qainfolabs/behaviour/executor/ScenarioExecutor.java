package org.qainfolabs.behaviour.executor;


public class ScenarioExecutor {
	
	protected Scenario scenario;
	protected StepExecutor stepExecutor;

	public ScenarioExecutor(Scenario scenario) {
		this.scenario = scenario;
		this.stepExecutor = new StepExecutor();
	}

	public void executeScenario() {
		java.util.Iterator<String> steps = scenario.allSteps().iterator();
		while(steps.hasNext()){
			stepExecutor.executeStep(steps.next());
		}
	}

}
