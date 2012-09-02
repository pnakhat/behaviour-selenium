package org.qainfolabs.behaviour.executor;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.qainfolabs.behaviour.selenium.utils.ScenarioExtractor;
import org.qainfolabs.behaviour.selenium.utils.StoryReader;

/**
 * Hello world!11
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    
    {
    	PropertyConfigurator.configure("log4j.properties");

    	Logger logger = Logger.getLogger("myapp");

    	StoryReader storyReader = new StoryReader("src/main/scenarios");
    	List<File> allStories = storyReader.getAllStories();
    	
    	for(int i=0;i< allStories.size();i++){
    		List<Scenario> allScenariosInStory = ScenarioExtractor.getAllScenarios(allStories.get(i));
			logger.info("Number of scenarios in story \": "
					+ allStories.get(i).getName() + "\" are - "
					+ allScenariosInStory.size());
    		Iterator<Scenario> scenario = allScenariosInStory.iterator();
    		while(scenario.hasNext()){
    			logger.info("Entering App");
    			ScenarioExecutor scenarioExecutor = new ScenarioExecutor(scenario.next());
    			scenarioExecutor.executeScenario();
    		}
    	}
    	
    }
}
