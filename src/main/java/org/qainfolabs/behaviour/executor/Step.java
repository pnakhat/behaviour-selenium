package org.qainfolabs.behaviour.executor;

import java.util.List;
import java.util.logging.Logger;

public class Step {


	private String step;
	private Logger logger;

	public Step(String step) {
		this.logger = Logger.getLogger("myapp");
		this.step = step;
	}

	public String getStep() {
		logger.info(step);
		return step;
	}

}
