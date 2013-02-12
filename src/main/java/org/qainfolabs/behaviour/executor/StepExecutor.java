package org.qainfolabs.behaviour.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.qainfolabs.behaviour.selenium.utils.ReadFileInBuffer;
import org.qainfolabs.behaviour.selenium.utils.StepDefinition;
import org.qainfolabs.behaviour.webdriver.WebDriverHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StepExecutor {
	protected StepDefinition stepDefinition;
    @Autowired(required = true)
	private WebDriverHelper helper;
    private static Logger LOGGER = Logger.getLogger(StepExecutor.class);
	
	public StepExecutor() {
		this.stepDefinition = new StepDefinition();
	}

	public void executeStep(Step step) {
		compileAndExecuteStep(step);
	}

    private void compileAndExecuteStep(Step step) {
        if (checkIgnorableList(step.getStepName())) {
            List<String> lowLevelSteps = null;
            try {
                lowLevelSteps = stepDefinition.getStepDefinition(step.getStepName());
                if (lowLevelSteps.size() > 0) {
                    LOGGER.info("Low level step for step: '" + step.getStepName() + "' is ->" + lowLevelSteps);
                    takeActionOnSteps(lowLevelSteps);
                } else {
                    LOGGER.info("No low level step definition found for step" + step);
                    step.setStatus("FAILED");
                }
            } catch (IOException e) {
                e.printStackTrace();
                step.setStatus("FAILED");
            }
        }
    }


	private void takeActionOnSteps(List<String> lowLevelSteps) {
		
		for (int i=0;i<lowLevelSteps.size() -1;i++){
			CommandExecutor ce = new CommandExecutor(lowLevelSteps.get(i));
			//helper
			ce.getCommands().exeute(helper);
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
