package org.qainfolabs.behaviour.executor;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.qainfolabs.behaviour.webdriver.StaticWebDriver;
import org.qainfolabs.behaviour.webdriver.WebDriverHelper;


public class ScenarioExecutor implements Runnable {
	
	protected Scenario scenario;
	protected StepExecutor stepExecutor;
	private Logger logger;
	private WebDriverHelper helper;

	public ScenarioExecutor(Scenario scenario) {
		this.scenario = scenario;
		this.helper = new WebDriverHelper(new StaticWebDriver().getDriver());
		this.stepExecutor = new StepExecutor(helper);
		this.logger = Logger.getLogger(ScenarioExecutor.class);

	}

	private void executeScenario() {
		Iterator<Step> steps = scenario.allSteps().iterator();
		while(steps.hasNext()){
			String stepToExecute = steps.next().getStep();
			logger.info("Step to execute : " + stepToExecute) ; 
			stepExecutor.executeStep(stepToExecute);
		}
		helper.closeBrowser();
	}

	public void run() {
		executeScenario();
	}

}
