package org.qainfolabs.behaviour.model;

import org.apache.log4j.Logger;
import org.qainfolabs.behaviour.executor.StepExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.log4j.Logger.getLogger;

@Component
public class Step {
	private String stepName;
    private String status;

    public  Step() {

    }

    @Autowired
    private StepExecutor stepExecutor;

    private static Logger LOGGER = Logger.getLogger(Step.class);

    public StackTraceElement[] getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(StackTraceElement[] stackTrace) {
        this.stackTrace = stackTrace;
    }

    private StackTraceElement[] stackTrace;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public Step(String step) {
		this.stepName = step;
	}

	public String getStepName() {
        LOGGER.info(stepName);
		return stepName;
	}

    public boolean hasParameters() {
        return stepName.matches(".*'.*$");
    }

    public void executeStep() {
        stepExecutor.executeStep(this);
    }
}
