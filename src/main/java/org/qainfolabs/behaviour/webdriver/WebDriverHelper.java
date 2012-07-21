package org.qainfolabs.behaviour.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverHelper {
	
	protected WebDriver driver;
	
	public WebDriverHelper() {
		this.driver = new ChromeDriver();
	}

}
