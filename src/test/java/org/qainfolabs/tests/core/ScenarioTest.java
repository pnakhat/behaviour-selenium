package org.qainfolabs.behaviour.tests;

import org.qainfolabs.behaviour.executor.Scenario;
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
		ArrayList<String> testScenario = new ArrayList<String>();
		testScenario.add("Scenario:");
		testScenario.add("Given I do something");
		testScenario.add("When I do something");
		testScenario.add("End Scenario:");
		oScenario.readSteps(testScenario);
		List<String> outScenario = oScenario.allSteps(); 
		Assert.assertEquals(outScenario.size(), 4);
	}	
	
	
	//test to determine number of files in current path
	@Test
	public void testScenarioExtractor(){		
		StoryReader oStoryReader = new StoryReader("src/test/java/org/qainfolabs/behaviour/tests/resource");
		List<File> testScenario = oStoryReader.getAllStories();		 
		Assert.assertEquals(testScenario.size(), 1);
	}
	
	//test to determine number of scenarios in a file
		@Test
		public void testScenarioCount() throws IOException{		
			StoryReader oStoryReader = new StoryReader("src/test/java/org/qainfolabs/behaviour/tests/resource");
			ScenarioExtractor oSextractor = new ScenarioExtractor();
			List<File> testScenario = oStoryReader.getAllStories();	
			List<Scenario> scenarios = oSextractor.getAllScenarios(testScenario.get(0));
			Assert.assertEquals(scenarios.size(), 2);
		}
	
}
