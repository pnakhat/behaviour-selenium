package org.qainfolabs.behaviour.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class StaticWebDriver {
	
	private static WebDriver driver;
	
	
	static  {
		get();
	}
	
	
	public void createDriver(){
		if (driver == null) {
			//System.setProperty("webdriver.firefox.bin", "/System");
			StaticWebDriver.driver = new FirefoxDriver();
		}
	}
	
	private static void get() {
		// TODO Auto-generated method stub - Changed 
		
	}

	public static WebDriver getDriver(){
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
