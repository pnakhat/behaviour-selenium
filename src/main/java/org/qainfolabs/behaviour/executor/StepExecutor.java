package org.qainfolabs.behaviour.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.qainfolabs.behaviour.selenium.utils.ReadFileInBuffer;
import org.qainfolabs.behaviour.selenium.utils.StepDefinition;

public class StepExecutor {
	protected StepDefinition stepDefinition;
	
	public StepExecutor() {
		this.stepDefinition = new StepDefinition();
	}

	public void executeStep(String step) {
		compileStep(step);
	}

	private void compileStep(String step) {
		if(checkIgnorableList(step)){
			List<String> lowLevelSteps = null;
			try {
				lowLevelSteps = stepDefinition.getStepDefinition(step);
				takeActionOnSteps(lowLevelSteps);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Low level step " + lowLevelSteps);
		}
	}


	private void takeActionOnSteps(List<String> lowLevelSteps) {
		
		for (int i=0;i<lowLevelSteps.size() -1;i++){
			CommandExecutor ce = new CommandExecutor(lowLevelSteps.get(i));
			ce.getCommands().exeute();
		}
	}

	private boolean checkIgnorableList(String step) {
		if(step.indexOf("Scenario") == 0){
			System.out.println("Can't execute step: " + step);
			return false;
		}
		
		if(step.indexOf("End Scenario") == 0){
			System.out.println("Can't execute step: " + step);
			return false;
		}
	return true;
	}

}
