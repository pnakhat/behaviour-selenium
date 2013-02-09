package org.qainfolabs.behaviour.executor;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
	
	List<Step> scenarioSteps;
	private String scenarioName;
    private String featureFileName;

    public Scenario(){
		this.scenarioSteps = new ArrayList<Step>();
	}
	
	
	public void readSteps(List<Step> scenarioSteps){
			this.scenarioSteps.addAll(scenarioSteps);
	}
	
	
	public List<Step> allSteps(){
		return scenarioSteps;
	}


	public void setTitle(String title) {
		this.scenarioName = title;
	}
	
	public String getTitle(){
		return scenarioName;
	}

    public void setStoryName(String featureFileName) {
        this.featureFileName = featureFileName;
    }

    public String FeatureFileName() {
        return featureFileName;
    }
}
