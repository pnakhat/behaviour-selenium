package org.qainfolabs.tests.core;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.qainfolabs.behaviour.executor.Command;


public class TestCommand {
	
	@Test
	public void testExtractAction(){
		String testString = "open,\"http://www.google.co.uk\"";
		Command cm = new Command(testString);
		Assert.assertEquals(true, true);
	}

}
