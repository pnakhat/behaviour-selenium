package org.qainfolabs.behaviour.executor;

import org.apache.log4j.Logger;
import org.qainfolabs.behaviour.webdriver.WebDriverHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Command {

	private String lowLeveStep;
	private String action;
	private String data;
	private String object;
	private String[] stepArray;

	private static final Logger LOGGER = Logger.getLogger(Command.class);

	//setText "IBM" name=q



    public Command init(String lowLevelStep) {
        this.lowLeveStep = lowLevelStep;
        System.setProperty("delim", ",");
        String delim = System.getProperty("delim");
        stepArray = lowLevelStep.split(delim);
        setAction();
        setObject();
        setData();
        return this;
    }


	
	
	private void setObject() {
		if(stepArray.length > 2){
			this.object = stepArray[2];
		}
        else {
            LOGGER.info("No object definition found in low level lowLeveStep - " + lowLeveStep);
            this.object = "";
        }
	}


	private void setData() {
		this.data  = stepArray[1];
	}


	private void setAction(){
		this.action = stepArray[0];
	}
	
	public String getAction(){
		return this.action;
	}
	
	public String getData(){
		return this.data.trim();
	}
	
	public String getObject(){
		return this.object;
	}


	public void exeute(WebDriverHelper helper) {
        LOGGER.info("Decoding Step: - " + lowLeveStep + ".....................................");
		LOGGER.info("Action: " + getAction());
		LOGGER.info("Data " + getData());
		LOGGER.info("UI Object " + getObject());
        LOGGER.info("Step Decoding finished...................................");
		//WebDriverHelper helper = new WebDriverHelper(DriverFactory.getDriver());
		helper.execute(getAction() , getData(), getObject());
		
	}


    public void replaceVariableNameWithValue(String data) {
        this.data = data;
    }
}
