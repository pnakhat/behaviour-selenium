package org.qainfolabs.behaviour.reporting;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import org.qainfolabs.behaviour.model.Step;

public class ReportGenerator {
    public String generateResultXml(ScenarioReportSchema scenarioReport) {
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
       // XStream xstream = new XStream();
        xstream.alias("Scenario",ScenarioReportSchema.class);
        xstream.useAttributeFor(ScenarioReportSchema.class, "scenarioName");
        xstream.alias("Step", Step.class);
        xstream.useAttributeFor(Step.class, "stepName");
        return xstream.toXML(scenarioReport);
    }
}
