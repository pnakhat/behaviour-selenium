package org.qainfolabs.behaviour.executor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.qainfolabs.behaviour.exceptions.NoLowLevelStepExists;
import org.qainfolabs.behaviour.model.Step;
import org.qainfolabs.behaviour.selenium.utils.LowLevelDefinition;
import org.qainfolabs.behaviour.webdriver.WebDriverHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StepExecutor {
    @Autowired(required = true)
	private WebDriverHelper helper;
    private static Logger LOGGER = Logger.getLogger(StepExecutor.class);
    private File stepDefFile;

    public StepExecutor() {
		this.stepDefFile = new File("src/main/scenarios/stepDef.def");
	}

	public void executeStep(Step step) {
		compileAndExecuteStep(step);
	}

    private void compileAndExecuteStep(Step step) {
        if (checkIgnorableList(step.getStepName())) {
            List<LowLevelStep> lowLevelSteps = null;
            try {
                LowLevelDefinition ld = new LowLevelDefinition(step.getStepName());
                lowLevelSteps = ld.getDefinitionOfStep(stepDefFile).getLowLevelSteps();
                HashMap<String, String> stepParaMeters = new HashMap<String, String>();
                if(step.hasParameters()) {
                    stepParaMeters =  ld.getStepParameterMap();
                }

                if (lowLevelSteps.size() > 0) {
                    LOGGER.info("Low level lowLeveStep for lowLeveStep: '" + step.getStepName() + "' is ->" + lowLevelSteps);
                    takeActionOnSteps(lowLevelSteps, stepParaMeters);
                } else {
                    String message = "No low level lowLeveStep definition found for lowLeveStep:-> " + step.getStepName();
                    LOGGER.info(message);
                    throw new NoLowLevelStepExists(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                step.setStatus("FAILED");
            } catch (NoLowLevelStepExists e) {
                e.printStackTrace();
                step.setStatus("SKIPPED");
            }
        }
    }


	private void takeActionOnSteps(List<LowLevelStep> lowLevelSteps, HashMap<String, String> stepParaMeters) {
		
		for (int i=0;i<lowLevelSteps.size() ;i++){
			CommandExecutor ce = new CommandExecutor(lowLevelSteps.get(i).getLowLevelStep());
            String parameName = ce.getCommands().getData();
            if(StringUtils.startsWith(parameName,"&")){
                ce.getCommands().replaceVariableNameWithValue(stepParaMeters.get(parameName.trim()));
                LOGGER.info("Parameter value replaced : " + parameName + " -> " + stepParaMeters.get(parameName.trim()));
            }
            ce.getCommands().exeute(helper);

		}
	}

	private boolean checkIgnorableList(String step) {
		if(step.indexOf("Scenario") == 0){
			System.out.println("Can't execute lowLeveStep: " + step);
			return false;
		}
		
		if(step.indexOf("End Scenario") == 0){
			System.out.println("Can't execute lowLeveStep: " + step);
			return false;
		}
	return true;
	}

}
