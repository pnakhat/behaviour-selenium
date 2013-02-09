package org.qainfolabs.behaviour.selenium.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class StepDefinition {
	protected LowLevelDefinition lowLevelDefinition;
	
	public StepDefinition() {
		this.lowLevelDefinition = new LowLevelDefinition();
	}


	public List<String> getStepDefinition(String step) throws IOException {
		File stepDefFile = new File("src/main/scenarios/stepDef.def");
		 System.out.println("Matching step............... " + step);
			List<String> lowLevelSteps = lowLevelDefinition.getDefinitionOfStep(step,stepDefFile);
		return lowLevelSteps;
	}
}
