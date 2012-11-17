package org.qainfolabs.behaviour.executor;

import org.apache.log4j.Logger;
import org.qainfolabs.behaviour.webdriver.WebDriverHelper;

public class Command {

	protected String step;
	protected String action;
	protected String data;
	protected String object;
	protected String[] stepArray;
	private static final Logger logger = Logger.getLogger(Command.class);

	//setText "IBM" name=q

	public Command(String lowLevelStep) {
		this.step = lowLevelStep;
		System.setProperty("delim", "|");
		stepArray = lowLevelStep.split(",");
		setAction();
		setObject();
		setData();
	}
	
	
	private void setObject() {
		if(stepArray.length > 2){
			this.object = stepArray[2];
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
		logger.info("I am action " + getAction());
		logger.info("I am Data " + getData());
		logger.info("I am UI Object " + getObject());		
		//WebDriverHelper helper = new WebDriverHelper(StaticWebDriver.getDriver());
		helper.execute(getAction() , getData(), getObject());
		
	}
	
	 

}
