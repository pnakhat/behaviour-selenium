package org.qainfolabs.behaviour.webdriver;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverHelper {
	
	protected WebDriver driver;
	
	public WebDriverHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void execute(String action, String data, String object) {
		if(action.equalsIgnoreCase("Open")){			
			driver.get(data);
		}
		
		if(action.equalsIgnoreCase("setText")){	
			
			String strategy = object.split("=")[0];
			String locator  = object.split("=")[1];
			WebElement element = null;
			if (strategy.equals("id")){
				element = driver.findElement(By.id(locator));
			}
			element.sendKeys(data);
		}
		
	}

	public void closeBrowser() {
		driver.close();
	}

}
