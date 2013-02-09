package org.qainfolabs.behaviour.reporting;

import com.thoughtworks.xstream.XStream;
import org.qainfolabs.behaviour.executor.Step;

import javax.xml.stream.XMLStreamConstants;
import java.util.ArrayList;
import java.util.List;


public class ScenarioReportSchema {
    private String scenarioName;
    private List<Step> steps = new ArrayList<Step>();

    public List<Step> getSteps() {
        return steps;
    }

    public void setStep(Step step) {
       if(!step.equals(null)){
           steps.add(step);
       }
    }



    public ScenarioReportSchema(String scenarioTitle) {
        this.scenarioName = scenarioTitle;
    }
}
