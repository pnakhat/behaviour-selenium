package org.qainfolabs.behaviour.tests;

import org.qainfolabs.behaviour.executor.LowLevelStep;
import org.qainfolabs.behaviour.executor.Step;
import org.qainfolabs.behaviour.selenium.utils.LowLevelDefinition;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;

public class StepTest {


    private LowLevelDefinition lowLevelDefinition;


    @BeforeClass
    public void setUp(){

    }


    @Test
    public void testScenarioExtractor() throws IOException {
        lowLevelDefinition = new LowLevelDefinition("Given I do something");
        List<LowLevelStep> testSteps = lowLevelDefinition.getDefinitionOfStep(new File("src/test/resources/stepDefTest.def")).getLowLevelSteps();
        Assert.assertEquals(testSteps.size(), 4);

    }


    @Test
    public void testStepRegex() {
        String step = "Given I have '20' apples and '10' oranges";
        lowLevelDefinition = new LowLevelDefinition(step);

        String result = lowLevelDefinition.createStepRegex();
        System.out.println(result);
        Assert.assertEquals(result, "Given I have '&[^\\s]*' apples and '&[^\\s]*' oranges");
    }


    @Test
    public void testStepDefMatchers() {
        String step = "Given I have '20' apples and '10' oranges";
        lowLevelDefinition = new LowLevelDefinition(step);
        String stepdef = "Given I have '&apples' apples and '&oranges' oranges";
        String stepRegesx = lowLevelDefinition.createStepRegex();
        assertTrue(stepdef.matches(stepRegesx));
    }


    @Test
    public void testLowLevelStepDefinitionFinder() throws IOException {
        File stepDefFile = new File("src/test/resources/stepDefTest.def");
        String step = "Given I have '20' jeans and '20' shorts";
        lowLevelDefinition = new LowLevelDefinition(step);
        List<LowLevelStep> lowLevelSteps = lowLevelDefinition.getDefinitionOfStep(stepDefFile).getLowLevelSteps();
        Assert.assertEquals(lowLevelSteps.size(), 3);
    }

    @Test
    public void testLowLevelStepDefinitionShouldNotFindAnySteps() throws IOException {
        File stepDefFile = new File("src/test/resources/stepDefTest.def");
        String step = "Given I search for 'jeans'";
        lowLevelDefinition = new LowLevelDefinition(step);
        List<LowLevelStep> lowLevelSteps = lowLevelDefinition.getDefinitionOfStep(stepDefFile).getLowLevelSteps();
        Assert.assertEquals(lowLevelSteps.size(), 0);
    }


    @Test
    public void testLowLevelStepDefinitionShouldFindSteps() throws IOException {
        File stepDefFile = new File("src/test/resources/stepDefTest.def");
        String step = "Given I search for 'jeans' and 'shorts'";
        lowLevelDefinition = new LowLevelDefinition(step);
        List<LowLevelStep> lowLevelSteps = lowLevelDefinition.getDefinitionOfStep(stepDefFile).getLowLevelSteps();
        Assert.assertEquals(lowLevelSteps.size(), 0);
    }


    @Test
    public void testLowLevelStepDefinitionFinderAnotherExampleMock() throws IOException {
        Step step = mock(Step.class);
        when(step.getStepName()).thenReturn("Given I do something");
        lowLevelDefinition = mock(LowLevelDefinition.class);
        List<String> a = new ArrayList<String>();
        a.add("a");
//        when(lowLevelDefinition.getStoryFileParameterValues()).thenReturn(a);

    }

    @Test
    public void testParameterNameExtraction () throws IOException {
       lowLevelDefinition = new LowLevelDefinition("Given I search for 'jeans' and find 'shirts'");
       HashMap<String, String> params = lowLevelDefinition.getDefinitionOfStep(new File("src/test/resources/stepDefTest.def")).getStepParameterMap();
       Assert.assertEquals(params.size(),2 );

    }

    @Test
    public void testIfStepHasParameters() {
        Step step = new Step("Given I have some '40'");
        assertTrue(step.hasParameters());
    }


}
