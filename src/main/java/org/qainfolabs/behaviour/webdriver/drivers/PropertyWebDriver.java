package org.qainfolabs.behaviour.webdriver.drivers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class PropertyWebDriver implements WebDriver {
	
	private WebDriver driver;

	public PropertyWebDriver() {
		this.driver =  PropertyBasedWebDriver();
	}

	private WebDriver PropertyBasedWebDriver() {
		WebDriver _driver = null;
		String browserToUse = System.getProperty("browser", "firefox");
		if (browserToUse.equalsIgnoreCase("FIREFOX")) {
			_driver = new FirefoxDriver();
		} else if (browserToUse.equalsIgnoreCase("CHROME")) {
			System.setProperty("webdriver.chrome.driver","D:\\Users\\pankaj\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
			_driver = new ChromeDriver();
		}else if (browserToUse.equalsIgnoreCase("IE")) {
			_driver = new InternetExplorerDriver();
		}

		return _driver;
	}

	public void close() {
		driver.close();
	}

	public WebElement findElement(By by) {
		return driver.findElement(by);
	}

	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	public void get(String url) {
		driver.get(url);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public Options manage() {
		return driver.manage();
	}

	public Navigation navigate() {
		return driver.navigate();
	}

	public void quit() {
		driver.quit();
	}

	public TargetLocator switchTo() {
		return driver.switchTo();
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

}
