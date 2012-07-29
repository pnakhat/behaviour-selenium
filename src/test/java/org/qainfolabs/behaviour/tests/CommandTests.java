package org.qainfolabs.behaviour.tests;

import org.qainfolabs.behaviour.executor.Command;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommandTests {
	
	@Test
	public void testExtractAction(){
		String testString = "open|\"http://www.google.co.uk\"";
		Command cm = new Command(testString);
		Assert.assertEquals(cm.getAction(), "open");
	}

}
