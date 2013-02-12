package org.qainfolabs.behaviour.tests;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.qainfolabs.behaviour.executor.Step;
import org.qainfolabs.behaviour.selenium.utils.LowLevelDefinition;
import org.qainfolabs.behaviour.selenium.utils.StepDefinition;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StepTest {


    private LowLevelDefinition lowLevelDefinition;

	/*
     * test to determine number of steps in current file
	 * result asserted may fail if there is change in stepdef.def file. 
	 * Currently src/main/scenarios/stepDef.def is hard coded in stepDefination.java 
	*/


    @BeforeClass
    public void setUp(){
      lowLevelDefinition = new LowLevelDefinition();
    }


    @Test
    public void testScenarioExtractor() throws IOException {
        StepDefinition oStepReader = new StepDefinition();
        List<String> testSteps = oStepReader.getStepDefinition("Given I do something");
        Assert.assertEquals(testSteps.size(), 4);

    }


    @Test
    public void testStepRegex() {
        String step = "Given I have \"20\" apples and \"10\" oranges";

        String result = lowLevelDefinition.createStepRegex(step);
        System.out.println(result);
        Assert.assertEquals(result, "Given I have .* apples and .* oranges");
    }


    @Test
    public void testStepDefMatchers() {
        String step = "Given I have \"20\" apples and \"10\" oranges";
        String stepdef = "Given I have $apples apples and $oranges oranges";
        String stepRegesx = lowLevelDefinition.createStepRegex(step);
        Assert.assertTrue(stepdef.matches(stepRegesx));
    }


    @Test
    public void testLowLevelStepDefinitionFinder() throws IOException {
        File stepDefFile = new File("src/test/resources/stepDef.def");
        String step = "Given I have \"20\" jeans and \"20\" shorts";
        List<String> lowLevelSteps = lowLevelDefinition.getDefinitionOfStep(step,stepDefFile);
        Assert.assertEquals(lowLevelSteps.size(), 3);
    }


}
