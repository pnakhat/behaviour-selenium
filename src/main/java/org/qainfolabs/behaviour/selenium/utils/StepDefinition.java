package org.qainfolabs.behaviour.selenium.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class StepDefinition {
	protected LowLevelDefinotion lowLevelDefinition;
	
	public StepDefinition() {
		this.lowLevelDefinition = new LowLevelDefinotion();
	}


	public List<String> getStepDefinition(String step) throws IOException {
		File stepDefFile = new File("src/main/scenarios/stepDef.def");
			List<String> lowLevelSteps = lowLevelDefinition.getDefinitionOfStep(step,stepDefFile);
			// TODO Auto-generated catch block
		return lowLevelSteps;
	}
}
