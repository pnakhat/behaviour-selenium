package org.qainfolabs.behaviour.executor;

import org.apache.log4j.Logger;

import static org.apache.log4j.Logger.getLogger;

public class Step {
	private String stepName;
    private String status;

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    private String stackTrace;
    private static Logger LOGGER = Logger.getLogger(Step.class);

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

}
