package org.qainfolabs.behaviour.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.qainfolabs.behaviour.webdriver.drivers.PropertyWebDriver;

public class DriverFactory {
	
	private WebDriver driver;

	
	
	public void createDriver(){
		if (driver == null) {
			this.driver = new PropertyWebDriver();
		}
	}
	


	public WebDriver getDriver(){
		createDriver();
		return driver;
	}

	public void closeBrowser() {
		if (driver != null) {
			System.out.println("Closing browser......");
			driver.close();
			driver = null;
		}
		
	}

}
