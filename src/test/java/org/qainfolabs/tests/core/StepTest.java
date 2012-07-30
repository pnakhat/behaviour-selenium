package org.qainfolabs.behaviour.tests;

import org.qainfolabs.behaviour.selenium.utils.StepDefinition;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class StepTest {
	
	/*
	 * test to determine number of steps in current file
	 * result asserted may fail if there is change in stepdef.def file. 
	 * Currently src/main/scenarios/stepDef.def is hard coded in stepDefination.java 
	*/
	
		@Test
		public void testScenarioExtractor() throws IOException{			
			StepDefinition  oStepReader = new StepDefinition();
			List<String> testSteps = oStepReader.getStepDefinition("Given I do something");					 
			Assert.assertEquals(testSteps.size(), 3);
		}

}
