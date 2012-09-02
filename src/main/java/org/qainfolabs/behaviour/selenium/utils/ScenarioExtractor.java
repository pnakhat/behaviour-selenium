package org.qainfolabs.behaviour.selenium.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.qainfolabs.behaviour.executor.Scenario;
import org.qainfolabs.behaviour.executor.Step;

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
			if(line.contains("Scenario") || line.contains("End Scenario")){
				foundScenarioStart = true;
				if(line.contains("End Scenario")){
					scn.readSteps(scenarioSteps);
					scenarios.add(scn);
					System.out.println("All steps" + scn.allSteps().toString());
				}
				scn = new Scenario();
				scenarioSteps = new ArrayList<Step>();
			}
			
			if(foundScenarioStart){
				scenarioSteps.add(new Step(line));
			}
			
			
    	}
		System.out.println("Number of scenarios in file" + file.getName() + scenarios.size());
		return scenarios;
	}

}
