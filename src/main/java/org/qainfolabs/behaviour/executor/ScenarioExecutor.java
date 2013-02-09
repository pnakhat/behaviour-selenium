package org.qainfolabs.behaviour.executor;

import java.util.Iterator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import org.apache.log4j.Logger;
import org.qainfolabs.behaviour.reporting.ReportGenerator;
import org.qainfolabs.behaviour.reporting.ScenarioReportSchema;
import org.qainfolabs.behaviour.utils.FileWriterUtil;
import org.qainfolabs.behaviour.webdriver.WebDriverHelper;
import org.qainfolabs.behaviour.webdriver.drivers.PropertyWebDriver;


public class ScenarioExecutor implements Runnable {
	
	protected Scenario scenario;
	protected StepExecutor stepExecutor;
	private Logger LOGGER;
	private WebDriverHelper helper;
    private ScenarioReportSchema scenarioReport;
    private final String featureFileName;
    private boolean didScenarioFailed = false;

    public ScenarioExecutor(Scenario scenario) {
		this.scenario = scenario;
		this.helper = new WebDriverHelper(new PropertyWebDriver());
		this.stepExecutor = new StepExecutor(helper);
		this.LOGGER = Logger.getLogger(ScenarioExecutor.class);
        this.scenarioReport = new ScenarioReportSchema(scenario.getTitle());
        this.featureFileName = scenario.FeatureFileName();

	}

	private void executeScenario() {
		Iterator<Step> steps = scenario.allSteps().iterator();
		while(steps.hasNext()){
            Step currentStep = steps.next();
			String stepToExecute = currentStep.getStepName();
            scenarioReport.setStep(currentStep);
            try{
                String path = "src/main/scenarios/" + "stepDef_"+featureFileName;
                if(!didScenarioFailed) {
                    LOGGER.info("Step to execute : " + stepToExecute) ;
                    stepExecutor.executeStep(currentStep);
                    currentStep.setStatus("PASSED");
                } else {
                    currentStep.setStatus("SKIPPED");
                }
            }catch (Exception e){
                didScenarioFailed = true;
                helper.closeBrowser();
                currentStep.setStatus("FAILED");
                currentStep.setStackTrace(e.getMessage());
                LOGGER.info(e.getMessage());
            }
		}

        String xml = new ReportGenerator().generateResultXml(scenarioReport);
        String fileName = scenario.getTitle().replaceAll(" ","")+".xml";
        LOGGER.info("File created " + FileWriterUtil.writeFile(fileName,xml));
        LOGGER.info(xml);
	}

    public void run() {
		executeScenario();
	}

}
