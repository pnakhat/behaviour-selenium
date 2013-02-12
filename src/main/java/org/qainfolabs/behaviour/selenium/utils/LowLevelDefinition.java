package org.qainfolabs.behaviour.selenium.utils;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LowLevelDefinition {
	
	protected List<String> lowLevelSteps;
	
	
	public LowLevelDefinition() {
	}

	public List<String> getDefinitionOfStep(String step, File file) throws IOException {

        step = createStepRegex(step);

        System.out.println("Replaced step " + step);


        this.lowLevelSteps = new ArrayList<String>();

		String stepToBeMatched = "Match \""+step +"\"";
		LineNumberReader lr = new LineNumberReader(new FileReader(file));
		int startLine = 0;
		boolean matchStart = false;
		String line;
		while((line = lr.readLine())!=null){

			if(line.matches(stepToBeMatched)){
				 startLine = lr.getLineNumber();
				 matchStart = true;
			}
			
			if(matchStart && (!(startLine == lr.getLineNumber())) && !line.contains("End Match")){
				lowLevelSteps.add(line);
			}
			
			if((line.equals("End Match")) && matchStart == true){
				matchStart = false;
				//readMatchedSteps(startLine, endLine, lr);
			}
		}
		return lowLevelSteps;
		
		
	}

    public String createStepRegex(String step) {
        String[]  parameters = StringUtils.substringsBetween(step, "\"", "\"");
        if(parameters!=null) {
            List<String> parametersList = Arrays.asList(parameters);
            for (String parameter : parametersList) {
                step = StringUtils.replace(step, "\"" + parameter + "\"", ".*");
            }
        }
        return step;
    }


}
