package org.qainfolabs.behaviour.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
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
    	StoryReader storyReader = new StoryReader("src/main/scenarios");
    	List<File> allStories = storyReader.getAllStories();
    	
    	for(int i=0;i< allStories.size();i++){
    		List<Scenario> allScenariosInStory = ScenarioExtractor.getAllScenarios(allStories.get(i));
    		Iterator<Scenario> scenario = allScenariosInStory.iterator();
    		while(scenario.hasNext()){
    			ScenarioExecutor scenarioExecutor = new ScenarioExecutor(scenario.next());
    			scenarioExecutor.executeScenario();
    		}
    	}
    	
    }
}
