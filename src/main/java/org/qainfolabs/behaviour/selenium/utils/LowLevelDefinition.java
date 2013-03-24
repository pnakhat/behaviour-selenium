package org.qainfolabs.behaviour.selenium.utils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.qainfolabs.behaviour.exceptions.InvalidParamterCountExceotion;
import org.qainfolabs.behaviour.executor.LowLevelStep;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;

public class LowLevelDefinition {


	private List<LowLevelStep> lowLevelSteps;

    private List<String> storyFileParameterValues;
    private String highLevelStep;
    private String stepregex;
    private List<String> matchedLowLevelParameterList;
    private String matchedLowLevelMethodName;

    public LowLevelDefinition(String highLevelStep){
        this.highLevelStep = highLevelStep;
        this.stepregex = createStepRegex();
    }



    public LowLevelDefinition getDefinitionOfStep(File file) throws IOException {

        this.lowLevelSteps = new ArrayList<LowLevelStep>();

		String stepToBeMatched = "Match \""+stepregex +"\"";
		LineNumberReader lr = new LineNumberReader(new FileReader(file));
		int startLine = 0;
		boolean matchStart = false;
		String line;
		while((line = lr.readLine())!=null){

			if(line.matches(stepToBeMatched)){
                extractParameterNameFromLowLevelMethodSignatureStep(line);
                startLine = lr.getLineNumber();
				 matchStart = true;
			}
			
			if(matchStart && (!(startLine == lr.getLineNumber())) && !line.contains("End Match")){
				lowLevelSteps.add(new LowLevelStep(line));
			}
			
			if((line.equals("End Match")) && matchStart == true){
				matchStart = false;
				//readMatchedSteps(startLine, endLine, lr);
			}
		}
		 return this;
	}

    public void extractParameterNameFromLowLevelMethodSignatureStep(String line) {
        matchedLowLevelParameterList = new ArrayList<String>();
        this.matchedLowLevelMethodName = line;
        String[] arr1 = StringUtils.substringsBetween(line, "'", "'");
        if(arr1!=null) {
            matchedLowLevelParameterList = Arrays.asList(arr1);
        }
    }

    public String createStepRegex() {
        String stepregex = highLevelStep;
        String[]  parameters = StringUtils.substringsBetween(highLevelStep, "'", "'");
        if(parameters!=null) {
          storyFileParameterValues = Arrays.asList(parameters);
            for (String parameter : storyFileParameterValues) {
                stepregex = StringUtils.replace(stepregex,  parameter , "&[^\\s]*");
            }
        }
        return stepregex;
    }



    public List<LowLevelStep> getLowLevelSteps() {
        return lowLevelSteps;
    }

    public HashMap<String, String> getStepParameterMap() {
        HashMap<String,String> map = new HashMap<String, String>();
        for(int i=0;i<=matchedLowLevelParameterList.size()-1;i++) {
            map.put(matchedLowLevelParameterList.get(i), storyFileParameterValues.get(i));
        }

        return map;
    }

}
