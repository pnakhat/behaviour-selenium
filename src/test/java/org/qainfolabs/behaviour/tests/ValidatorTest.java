package org.qainfolabs.behaviour.tests;

import org.qainfolabs.behaviour.validator.ValidateScenarios;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ValidatorTest {


    @Test
    public void testInvalidStoryNoEndScenarioThrowsException() throws IOException {

        ValidateScenarios vs = new ValidateScenarios(new File("src/test/resources/story_invalid.story"));

        try {
            vs.validateScenarioSchema();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Scenario doesn't have end scenarioScenario: 1", e.getMessage());
        }

    }

    @Test
    public void testInvalidStoryNoStartScenarioThrowsException() throws IOException {

        ValidateScenarios vs = new ValidateScenarios(new File("src/test/resources/story_invalid_no_scenario_start.story"));

        try {
            vs.validateScenarioSchema();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Scenario doesn't have start scenario", e.getMessage());
        }

    }

}
