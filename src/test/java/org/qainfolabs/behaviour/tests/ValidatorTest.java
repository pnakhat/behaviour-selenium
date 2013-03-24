package org.qainfolabs.behaviour.tests;

import org.qainfolabs.behaviour.validator.ValidateScenarios;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: pankajnakhat
 * Date: 03/03/2013
 * Time: 10:02
 * To change this template use File | Settings | File Templates.
 */
public class ValidatorTest {


     @Test
    public void testInvalidStoryThrowsException() throws IOException {

         ValidateScenarios vs = new ValidateScenarios(new File("src/test/resources/story_invalid.story")) ;
         try {
             vs.validateScenarioSchema();
         } catch(IllegalArgumentException e) {
             Assert.assertEquals("Scenario doesnt have end scenarioScenario: 1", e.getMessage());

         }


    }

}
