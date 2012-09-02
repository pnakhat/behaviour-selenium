package org.qainfolabs.behaviour.tests;

import org.qainfolabs.behaviour.executor.Scenario;
import org.qainfolabs.behaviour.executor.Step;
import org.qainfolabs.behaviour.selenium.utils.StoryReader;
import org.qainfolabs.behaviour.selenium.utils.ScenarioExtractor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScenarioTest {
	
	//Extracting scenarios
	@Test
	public void testScenario(){
		Scenario oScenario = new Scenario();
		List<Step> testScenario = new ArrayList<Step>();
		testScenario.add(new Step("Scenario:"));
		testScenario.add(new Step("Given I do something"));
		testScenario.add(new Step("When I do something"));
		testScenario.add(new Step("End Scenario:"));
		oScenario.readSteps(testScenario);
		List<Step> outScenario = oScenario.allSteps(); 
		Assert.assertEquals(outScenario.size(), 4);
	}	
	
	
	//test to determine number of files in current path
	@Test
	public void testScenarioExtractor(){		
		StoryReader oStoryReader = new StoryReader("src/test/java/org/qainfolabs/tests/core/resource/");
		List<File> testScenario = oStoryReader.getAllStories();		 
		Assert.assertEquals(testScenario.size(), 1);
	}
	
	//test to determine number of scenarios in a file
		@Test
		public void testScenarioCount() throws IOException{		
			StoryReader oStoryReader = new StoryReader("src/test/java/org/qainfolabs/tests/core/resource");
			ScenarioExtractor oSextractor = new ScenarioExtractor();
			List<File> testScenario = oStoryReader.getAllStories();	
			List<Scenario> scenarios = oSextractor.getAllScenarios(testScenario.get(0));
			Assert.assertEquals(scenarios.size(), 2);
		}
	
}
