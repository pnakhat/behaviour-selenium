package org.qainfolabs.behaviour.executor;

import org.qainfolabs.behaviour.webdriver.WebDriverHelper;

public class Command {

	protected String step;
	protected String action;
	protected String data;
	protected String object;
	protected String[] stepArray;

	public Command(String step) {
		this.step = step;
		stepArray = step.split(" ");
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
		return this.data;
	}
	
	public String getObject(){
		return this.object;
	}


	public void exeute() {
		System.out.println(getAction());
		System.out.println(getData());
		System.out.println(getObject());
		//WebDriverHelper helper = new WebDriverHelper();
	}
	
	 

}
