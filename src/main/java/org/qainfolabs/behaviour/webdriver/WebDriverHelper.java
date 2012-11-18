package org.qainfolabs.behaviour.webdriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.qainfolabs.behaviour.selenium.utils.SeleniumCommandEnum;
import org.qainfolabs.behaviour.webdriver.drivers.PropertyWebDriver;

public class WebDriverHelper {
	
	protected PropertyWebDriver driver;
    private static final Logger LOGGER = Logger.getLogger(WebDriverHelper.class);
	
	public WebDriverHelper(WebDriver driver) {
		this.driver = (PropertyWebDriver) driver;
	}

	public void execute(String action, String data, String object) {
		String strategy = "";
		String locator = "";
		//Object can be null for command like open url
		if(object!=null){
			strategy = object.split("=")[0];
            try{
                locator  = object.split("=")[1];
            } catch (Exception e){
                LOGGER.info("Could not find locator in Object: " + object);
            }
		}
		
		WebElement element = null;
		
		if (strategy.equalsIgnoreCase("id")){
			element = driver.findElement(By.id(locator));
		}
		if (strategy.equalsIgnoreCase("xpath")){
			element = driver.findElement(By.xpath(locator));
		}
		
		SeleniumCommandEnum COMMAND = SeleniumCommandEnum.getCommandEnum(action);
        takeWebdriverAction(data, element, COMMAND);

	}

    private void takeWebdriverAction(String data, WebElement element, SeleniumCommandEnum COMMAND) {
        switch (COMMAND) {
        case OPEN:
            driver.get(data);
            return ;
        case SETTEXT:
            element.sendKeys(data);
            return;
        case CLICK:
            element.click();
             return;
        default:
            break;
        }
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
