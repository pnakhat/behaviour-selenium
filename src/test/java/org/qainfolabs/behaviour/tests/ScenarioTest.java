package org.qainfolabs.behaviour.tests;

import org.junit.Assert;
import org.junit.Test;
import org.qainfolabs.behaviour.model.Feature;
import org.qainfolabs.behaviour.model.Scenario;
import org.qainfolabs.behaviour.model.Step;
import org.qainfolabs.behaviour.selenium.utils.FeatureReader;
import org.qainfolabs.behaviour.selenium.utils.ScenarioExtractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScenarioTest {
	
	//Extracting scenarios
	@Test
	public void testScenario(){
		Scenario oScenario = new Scenario();
		List<Step> testScenario = stepData();
		oScenario.readSteps(testScenario);
		List<Step> outScenario = oScenario.allSteps(); 
		Assert.assertEquals(outScenario.size(), 4);
	}


	public List<Step> stepData() {
		List<Step> testScenario = new ArrayList<Step>();
		testScenario.add(new Step("Scenario: I am title"));
		testScenario.add(new Step("Given I do something"));
		testScenario.add(new Step("When I do something"));
		testScenario.add(new Step("End Scenario:"));
		return testScenario;
	}	
	
	
	@Test
	public void testScenarioTitle(){
		Scenario sc = new Scenario();
		List<Step> testScenario = stepData();
		sc.readSteps(testScenario);
		sc.setTitle("Scenario: I am title");
		Assert.assertEquals(sc.getTitle(), "Scenario: I am title");
		
	}
	
	//test to determine number of files in current path
	@Test
	public void testScenarioExtractor(){		
		FeatureReader oFeatureReader = new FeatureReader("src/test/java/org/qainfolabs/tests/core/resource/");
		List<Feature> testScenario = oFeatureReader.getAllFeatures();
		Assert.assertEquals(testScenario.size(), 1);
	}

    //test to determine number of scenarios in a file
    @Test
    public void testScenarioCount() throws IOException {
        FeatureReader oFeatureReader = new FeatureReader("src/test/java/org/qainfolabs/tests/core/resource");
        ScenarioExtractor oSextractor = new ScenarioExtractor();
        List<Feature> allStories = oFeatureReader.getAllFeatures();
        List<Scenario> scenarios = allStories.get(0).getAllScenarios();
        Assert.assertEquals(scenarios.size(), 3);
        Assert.assertEquals("AA", scenarios.get(0).getTitle(), "Scenario:");
    }

}
