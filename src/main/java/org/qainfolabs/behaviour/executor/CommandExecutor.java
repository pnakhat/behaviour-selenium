package org.qainfolabs.behaviour.executor;

public class CommandExecutor {
	
	public String step;
	public Command command;

	public CommandExecutor(String step) {
		this.step = step;
		this.command = new Command(step);
	}

	public Command getCommands() {
		return command;
	}
	
	
	
}
