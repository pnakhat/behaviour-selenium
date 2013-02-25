package org.qainfolabs.behaviour.executor;

import org.apache.log4j.Logger;

import static org.apache.log4j.Logger.getLogger;

public class Step {
	private String stepName;
    private String status;
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
}
