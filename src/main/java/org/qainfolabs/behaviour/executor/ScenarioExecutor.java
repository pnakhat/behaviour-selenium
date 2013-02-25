package org.qainfolabs.behaviour.executor;

import org.apache.log4j.Logger;
import org.qainfolabs.behaviour.reporting.ReportGenerator;
import org.qainfolabs.behaviour.reporting.ScenarioReportSchema;
import org.qainfolabs.behaviour.utils.FileWriterUtil;
import org.qainfolabs.behaviour.webdriver.WebDriverHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
@Scope("prototype")
public class ScenarioExecutor implements Runnable  {
	
	private Scenario scenario;
	private static Logger LOGGER  = Logger.getLogger(ScenarioExecutor.class);
    @Autowired
	public WebDriverHelper helper;
    private ScenarioReportSchema scenarioReport;
    private String featureFileName;
    @Autowired
    private StepExecutor stepExecutor;

    private boolean didScenarioFailed = false;


    public ScenarioExecutor init() {
        this.scenarioReport = new ScenarioReportSchema(scenario.getTitle());
        this.featureFileName = scenario.FeatureFileName();
        return this;
    }

    public void executeScenario() {

        String path = "src/main/scenarios/" + "stepDef_"+featureFileName;
		Iterator<Step> steps = scenario.allSteps().iterator();
		while(steps.hasNext()){
            Step currentStep = steps.next();
			String stepToExecute = currentStep.getStepName();
            scenarioReport.setStep(currentStep);
            try{

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
                currentStep.setStackTrace(e.getStackTrace());
                e.printStackTrace();
                LOGGER.info(e.getMessage());
            }
		}
        helper.closeBrowser();
        String xml = new ReportGenerator().generateResultXml(scenarioReport);
        String fileName = scenario.getTitle().replaceAll(" ","")+".xml";
        LOGGER.info("File created " + FileWriterUtil.writeFile(fileName,xml));
        LOGGER.info(xml);
	}

    public void run() {
		executeScenario();
	}

    public ScenarioExecutor setScenario(Scenario scenario) {
        this.scenario = scenario;
        return this;
    }
}
