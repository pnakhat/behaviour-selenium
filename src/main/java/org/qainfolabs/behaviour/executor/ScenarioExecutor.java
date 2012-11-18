package org.qainfolabs.behaviour.executor;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.thoughtworks.xstream.XStream;
import org.apache.log4j.Logger;
import org.qainfolabs.behaviour.reporting.ScenarioReportSchema;
import org.qainfolabs.behaviour.utils.FileWriterUtil;
import org.qainfolabs.behaviour.webdriver.StaticWebDriver;
import org.qainfolabs.behaviour.webdriver.WebDriverHelper;


public class ScenarioExecutor implements Runnable {
	
	protected Scenario scenario;
	protected StepExecutor stepExecutor;
	private Logger LOGGER;
	private WebDriverHelper helper;
    private ScenarioReportSchema scenarioReport;

	public ScenarioExecutor(Scenario scenario) {
		this.scenario = scenario;
		this.helper = new WebDriverHelper(new StaticWebDriver().getDriver());
		this.stepExecutor = new StepExecutor(helper);
		this.LOGGER = Logger.getLogger(ScenarioExecutor.class);
        this.scenarioReport = new ScenarioReportSchema(scenario.getTitle());

	}

	private void executeScenario() {
		Iterator<Step> steps = scenario.allSteps().iterator();
		while(steps.hasNext()){
            Step currentStep = steps.next();
			String stepToExecute = currentStep.getStepName();
            scenarioReport.setStep(currentStep);
			LOGGER.info("Step to execute : " + stepToExecute) ;
            try{
			    stepExecutor.executeStep(currentStep);
                currentStep.setStatus("PASSED");
            }catch (Exception e){
                currentStep.setStatus("FAILED");
                currentStep.setStackTrace(e.getMessage());
                LOGGER.info(e.getMessage());
            }
		}
		helper.closeBrowser();
        String xml = generateResultXml();
        String fileName = scenario.getTitle().replaceAll(" ","")+".xml";
        LOGGER.info("File created " + FileWriterUtil.writeFile(fileName,xml));
        LOGGER.info(xml);
	}

    private String generateResultXml() {
        XStream xstream = new XStream();
        xstream.alias("Scenario",ScenarioReportSchema.class);
        xstream.useAttributeFor(ScenarioReportSchema.class, "scenarioName");
        xstream.alias("Step", Step.class);
        xstream.useAttributeFor(Step.class, "stepName");
        File f = new File(scenario.getTitle().replaceAll(" ","")+".xml");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return xstream.toXML(scenarioReport);
    }

    public void run() {
		executeScenario();
	}

}
