package org.qainfolabs.behaviour.selenium.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.qainfolabs.behaviour.executor.Scenario;
import org.qainfolabs.behaviour.executor.Step;
import org.springframework.stereotype.Component;

public class ScenarioExtractor {

	public static List<Scenario> getAllScenarios(File file) throws IOException {
		ReadFileInBuffer rf = new ReadFileInBuffer();
    	BufferedReader bf = rf.readFile(file);
		String line;
		List<Step> scenarioSteps = null;
		List<Scenario> scenarios = new ArrayList<Scenario>();
		Scenario scn = null;
		boolean foundScenarioStart = false;
		while((line = bf.readLine())!=null){
			if(line.startsWith("Scenario")){
				foundScenarioStart = true;
				scn = new Scenario();
				scn.setTitle(line);
                scn.setStoryName(file.getName());
				scenarioSteps = new ArrayList<Step>();
			}
			if(line.contains("End Scenario")){
				scn.readSteps(scenarioSteps);
				scenarios.add(scn);
				System.out.println("All steps" + scn.allSteps().toString());
			}
			
			if(foundScenarioStart){
				scenarioSteps.add(new Step(line));
			}
			
			
    	}
		return scenarios;
	}

}
