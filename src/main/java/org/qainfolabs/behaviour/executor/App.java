package org.qainfolabs.behaviour.executor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.qainfolabs.behaviour.model.Feature;
import org.qainfolabs.behaviour.model.Scenario;
import org.qainfolabs.behaviour.selenium.utils.FeatureReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    private static Logger logger = Logger.getLogger(App.class);


    public static void main(String[] args) throws IOException

    {

        runTests();
    }

    private static void runTests() throws IOException {
        PropertyConfigurator.configure("log4j.properties");
        List<Feature> allFeatures = getAllFeatures();
        executeStories(allFeatures);
    }

    public static List<Feature> getAllFeatures() {
        return new FeatureReader("src/main/scenarios").getAllFeatures();
    }

    private static void executeStories(List<Feature> features) throws IOException {
        for (Feature feature:features) {

            List<Scenario> allScenariosInStory = feature
                    .getAllScenarios();
            logger.info("Number of scenarios in story \": "
                    + feature.featureFileName() + "\" are  "
                    + allScenariosInStory.size());

            for (Scenario scenario: feature.getAllScenarios()) {
                AnnotationConfigApplicationContext ctx = getAnnotationConfigApplicationContext();

                ScenarioExecutor scenarioExecutor = (ScenarioExecutor) ctx.getBean(ScenarioExecutor.class);
                scenarioExecutor.setScenario(scenario).init();
                scenarioExecutor.executeScenario();
            }

        }
    }

    private static AnnotationConfigApplicationContext getAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("org.qainfolabs");
        ctx.refresh();
        return ctx;
    }
}
