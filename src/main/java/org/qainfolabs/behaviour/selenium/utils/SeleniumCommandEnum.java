package org.qainfolabs.behaviour.selenium.utils;


public enum SeleniumCommandEnum {
	
	OPEN("open"),
	SETTEXT("setText"),
	CLICK("click"), VERIFYTEXT("verifyText");
	
	private String command;

	private SeleniumCommandEnum(String command){
		this.setCommand(command);
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	public static SeleniumCommandEnum getCommandEnum(String commandText){
		for(SeleniumCommandEnum commandEnum: SeleniumCommandEnum.values()){
			if(commandEnum.getCommand().equals(commandText)){
				return commandEnum;
			}
		}
		return null;
	}
	

}
