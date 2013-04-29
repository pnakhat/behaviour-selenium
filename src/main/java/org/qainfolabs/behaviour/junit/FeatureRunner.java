package org.qainfolabs.behaviour.junit;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.qainfolabs.behaviour.junit.ScenarioRunner;
import org.qainfolabs.behaviour.model.Feature;
import org.qainfolabs.behaviour.model.Scenario;

import java.util.ArrayList;
import java.util.List;


public class FeatureRunner extends ParentRunner<ParentRunner> {
    private final Feature feature;
    private final List<ParentRunner> children = new ArrayList<ParentRunner>();
    private Description description;

    protected FeatureRunner(Feature file) throws InitializationError {
        super(null);
        this.feature = file;
        getAllScenariosOutAndBuildChildren();
    }

    @Override
    protected List<ParentRunner> getChildren() {
        return children;
    }


    @Override
    public Description getDescription() {
        description = Description.createSuiteDescription(feature.getFeatureTitle());
        for(ParentRunner child: children) {
           description.addChild(describeChild(child));
        }
       return description;
    }

    @Override
    protected Description describeChild(ParentRunner child) {
        return child.getDescription();
    }

    @Override
    protected void runChild(ParentRunner child, RunNotifier notifier) {
        child.run(notifier);
    }

    @Override
    public void run(RunNotifier notifier) {
        super.run(notifier);
    }

    private void getAllScenariosOutAndBuildChildren() {
        for (Scenario scenario : feature.getAllScenarios()) {
            ParentRunner scenarioRunner = null;
            try {
                scenarioRunner = new ScenarioRunner(scenario);
            } catch (InitializationError initializationError) {
                initializationError.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            children.add(scenarioRunner);
        }

    }
}

