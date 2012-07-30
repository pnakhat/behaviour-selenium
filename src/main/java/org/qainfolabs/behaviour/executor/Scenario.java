package org.qainfolabs.behaviour.executor;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
	
	List<String> scenarioSteps = new ArrayList<String>();
	
	public void readSteps(List<String> scenarioSteps){
		this.scenarioSteps = scenarioSteps;
	}
	
	
	public List<String> allSteps(){
		return scenarioSteps;
	}

}
