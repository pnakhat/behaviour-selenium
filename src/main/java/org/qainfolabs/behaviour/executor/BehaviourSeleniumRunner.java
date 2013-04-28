package org.qainfolabs.behaviour.executor;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.qainfolabs.behaviour.model.Feature;
import org.qainfolabs.behaviour.selenium.utils.FeatureReader;

import java.util.ArrayList;
import java.util.List;


public class BehaviourSeleniumRunner extends ParentRunner<FeatureRunner> {

    private final List<FeatureRunner> children = new ArrayList<FeatureRunner>();

    public BehaviourSeleniumRunner(Class testClass) throws InitializationError {
        super(testClass);
        addChildren();
    }

    @Override
    protected List<FeatureRunner> getChildren() {
        return children;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected Description describeChild(FeatureRunner child) {
        return child.getDescription();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void runChild(FeatureRunner child, RunNotifier notifier) {
        child.run(notifier);
    }


    @Override
    public void run(RunNotifier notifier) {
        super.run(notifier);
    }


    protected void addChildren() throws InitializationError {
        List<Feature> features = new FeatureReader("src/main/scenarios").getAllFeatures();

        for (Feature feature : features) {
            children.add(new FeatureRunner(feature));
        }

    }


}
