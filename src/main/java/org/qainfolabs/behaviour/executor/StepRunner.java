package org.qainfolabs.behaviour.executor;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.qainfolabs.behaviour.model.Step;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pankajnakhat
 * Date: 28/04/2013
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public class StepRunner extends ParentRunner {

    protected StepRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected List getChildren() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public void run(RunNotifier notifier) {

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
