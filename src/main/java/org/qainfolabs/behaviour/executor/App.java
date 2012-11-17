package org.qainfolabs.behaviour.executor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.qainfolabs.behaviour.selenium.utils.ScenarioExtractor;
import org.qainfolabs.behaviour.selenium.utils.StoryReader;
import org.testng.junit.JUnit3TestRecognizer;
import org.testng.junit.JUnitTestRunner;

/**
 * Hello world!11
 *
 */
public class App{
	private static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) throws IOException

	{
		PropertyConfigurator.configure("log4j.properties");

		StoryReader storyReader = new StoryReader("src/main/scenarios");
		List<File> allStories = storyReader.getAllStories();

		for (int i = 0; i < allStories.size(); i++) {
			List<Scenario> allScenariosInStory = ScenarioExtractor
					.getAllScenarios(allStories.get(i));
			logger.info("Number of scenarios in story \": "
					+ allStories.get(i).getName() + "\" are  "
					+ allScenariosInStory.size());
			// Iterator<Scenario> scenario = allScenariosInStory.iterator();
			List<Thread> threads = new ArrayList<Thread>();

			for (int j = 0; j < allScenariosInStory.size(); j++) {
				ScenarioExecutor scenarioExecutor = new ScenarioExecutor(
						allScenariosInStory.get(j));
				threads.add(new Thread(scenarioExecutor, "Thread Name-"
						+ System.currentTimeMillis()));
			}

			for (int t = 0; t < threads.size(); t++) {
				logger.info("Executing thread " + threads.get(t).getName());
				threads.get(t).start();
			}

			// while(scenario.hasNext()){
			//
			// //scenarioExecutor.executeScenario();
			// }
		}

	}
}
