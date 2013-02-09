package org.qainfolabs.behaviour.selenium.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class LowLevelDefinition {
	
	protected List<String> lowLevelSteps;
	
	
	public LowLevelDefinition() {
	}

	public List<String> getDefinitionOfStep(String step, File file) throws IOException {
		this.lowLevelSteps = new ArrayList<String>();
		String stepToBeMatched = "Match \""+step +"\"";
		LineNumberReader lr = new LineNumberReader(new FileReader(file));
		int startLine = 0;
		boolean matchStart = false;
		String line;
		while((line = lr.readLine())!=null){
			if(line.equals(stepToBeMatched)){
				 startLine = lr.getLineNumber();
				 matchStart = true;
			}
			
			if(matchStart && (!(startLine == lr.getLineNumber()))){
				lowLevelSteps.add(line);
			}
			
			if((line.equals("End Match")) && matchStart == true){
				matchStart = false;
				//readMatchedSteps(startLine, endLine, lr);
			}
		}
		return lowLevelSteps;
		
		
	}
	

	


}