package org.qainfolabs.behaviour.webdriver;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverHelper {
	
	protected WebDriver driver;
	
	public WebDriverHelper() {
		this.driver = new FirefoxDriver();
	}

	public void execute(String action, String data, String object) {
		if(action.equalsIgnoreCase("Open")){			
			driver.get(data);
		}
		
	}

}
