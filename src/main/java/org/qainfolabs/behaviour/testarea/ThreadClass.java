package org.qainfolabs.behaviour.testarea;

public class ThreadClass implements Runnable {

	public void run() {
		printMessage();
	}

	private void printMessage() {
		System.out.println("I am coming from Thread");
	}

}
