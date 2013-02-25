package org.qainfolabs.behaviour.executor;

import java.util.List;

public class CommandExecutor {
	
	public String step;
	public Command command;

	public CommandExecutor(String step) {
		this.step = step;
		this.command = new Command().init(step);
	}

	public Command getCommands() {
		return command;
	}
	
	
	
}
