package org.qainfolabs.behaviour.executor;

import org.apache.log4j.PropertyConfigurator;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.qainfolabs.behaviour.model.Scenario;
import org.qainfolabs.behaviour.model.Step;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class ScenarioRunner extends ParentRunner<StepRunner> {
    private final Scenario scenario;
    private Description description;
    private List<StepRunner> children;

    public ScenarioRunner(Scenario scenario) throws InitializationError {
        super(ScenarioRunner.class);
        this.scenario = scenario;
    }

    @Override
    protected List<StepRunner> getChildren() {
        return children;
    }


    @Override
    public Description getDescription() {
      description =  Description.createSuiteDescription(scenario.getTitle());
      return description;
    }


    @Override
    public Description describeChild(StepRunner child) {
        return child.getDescription();
    }

    @Override
    protected void runChild(StepRunner child, RunNotifier notifier) {
        child.run(notifier);
    }


    @Override
    public void run(RunNotifier runNotifier) {
        PropertyConfigurator.configure("log4j.properties");
        AnnotationConfigApplicationContext ctx = getAnnotationConfigApplicationContext();
        EachTestNotifier eachTestNotifier = new EachTestNotifier(runNotifier, getDescription());
        eachTestNotifier.fireTestStarted();
        ScenarioExecutor scenarioExecutor = (ScenarioExecutor) ctx.getBean(ScenarioExecutor.class);
        scenarioExecutor.setScenario(scenario).init();
        scenarioExecutor.executeScenario();
//        scenarioExecutor.run();
//        scenario.run();
        eachTestNotifier.fireTestFinished();
    }

    private static AnnotationConfigApplicationContext getAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("org.qainfolabs");
        ctx.refresh();
        return ctx;
    }

}
