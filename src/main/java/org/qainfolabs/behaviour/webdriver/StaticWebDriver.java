package org.qainfolabs.behaviour.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class StaticWebDriver {
	
	private WebDriver driver;

	
	
	public void createDriver(){
		if (driver == null) {
			this.driver = new FirefoxDriver();
		}
	}
	


	public WebDriver getDriver(){
		createDriver();
		return driver;
	}

	public void closeBrowser() {
		if (driver != null) {
			System.out.println("CLosing browser......");
			driver.close();
			driver = null;
		}
		
	}

}
