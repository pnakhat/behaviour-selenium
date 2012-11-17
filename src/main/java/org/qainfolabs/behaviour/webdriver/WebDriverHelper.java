package org.qainfolabs.behaviour.webdriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.qainfolabs.behaviour.selenium.utils.SeleniumCommandEnum;
import org.qainfolabs.behaviour.webdriver.drivers.PropertyWebDriver;

public class WebDriverHelper {
	
	protected PropertyWebDriver driver;
	
	public WebDriverHelper(WebDriver driver) {
		this.driver = (PropertyWebDriver) driver;
	}

	public void execute(String action, String data, String object) {
		String strategy = "";
		String locator = "";
		//Object can be null for command like open url
		if(object!=null){
			strategy = object.split("=")[0];
			locator  = object.split("=")[1];
		}
		
		WebElement element = null;
		
		if (strategy.equalsIgnoreCase("id")){
			element = driver.findElement(By.id(locator));
		}
		if (strategy.equalsIgnoreCase("xpath")){
			element = driver.findElement(By.xpath(locator));
		}
		
		SeleniumCommandEnum COMMAND = SeleniumCommandEnum.getCommandEnum(action);
		
		switch (COMMAND) {
		case OPEN:
			driver.get(data);
		case SETTEXT:
			element.sendKeys(data);
		case CLICK:
			element.click();
			
		default:
			break;
		}
		
//
//		if(action.equalsIgnoreCase("Open")){			
//			driver.get(data);		
//			//driver.takeScreenshot();
//		}
//		
//		if(action.equalsIgnoreCase("setText")){	
//			element.sendKeys(data);
//		}
//		if(action.equalsIgnoreCase("click")){	
//			element.click();
//		}
//		
	}

	public void takeScreenshot() {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			System.currentTimeMillis();
			FileUtils.copyFile(scrFile, new File("c:\\tmp\\"+System.currentTimeMillis()+"screenshot1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeBrowser() {
		driver.close();
	}

}
