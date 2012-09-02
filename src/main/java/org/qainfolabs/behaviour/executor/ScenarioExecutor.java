package org.qainfolabs.behaviour.executor;

import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.library.Logger;
import org.qainfolabs.behaviour.webdriver.StaticWebDriver;


public class ScenarioExecutor {
	
	protected Scenario scenario;
	protected StepExecutor stepExecutor;

	public ScenarioExecutor(Scenario scenario) {
		this.scenario = scenario;
		this.stepExecutor = new StepExecutor();
	}

	public void executeScenario() {
		StaticWebDriver driver = new StaticWebDriver();
		driver.createDriver();
		Iterator<Step> steps = scenario.allSteps().iterator();
		while(steps.hasNext()){
			stepExecutor.executeStep(steps.next().getStep());
		}
		driver.closeBrowser();
	}

}
