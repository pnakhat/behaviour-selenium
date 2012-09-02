package org.qainfolabs.behaviour.executor;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
	
	List<Step> scenarioSteps;
	public Scenario(){
		this.scenarioSteps = new ArrayList<Step>();
	}
	
	
	public void readSteps(List<Step> scenarioSteps){
			this.scenarioSteps.addAll(scenarioSteps);
	}
	
	
	public List<Step> allSteps(){
		return scenarioSteps;
	}

}
