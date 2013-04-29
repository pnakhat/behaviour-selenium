package org.qainfolabs.behaviour.executor;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.qainfolabs.behaviour.model.Step;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class StepRunner extends ParentRunner {


    private final Step step;

    @Autowired
    private StepExecutor stepExecutor;

    protected StepRunner(Step step) throws InitializationError {
        super(null);
//        super(StepRunner.class);
        this.step = step;
    }

    @Override
    protected List getChildren() {
        return null;
    }



    public Description getDescription() {
      return Description.createSuiteDescription(step.getStepName());
    }


    @Override
    public void run(RunNotifier notifier) {
        stepExecutor.executeStep(step);
//        step.executeStep();
    }



    @Override
    protected Description describeChild(Object child) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void runChild(Object child, RunNotifier notifier) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
