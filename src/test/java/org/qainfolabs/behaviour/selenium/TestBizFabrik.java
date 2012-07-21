package org.qainfolabs.behaviour.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestBizFabrik {
	
	public WebDriver driver;
	
	@BeforeSuite
	public void createBrowser() {
		this.driver = new FirefoxDriver();
	}
	
	
	@BeforeTest
	public void login() throws InterruptedException{
		driver.get("http://test.bizfabrik.com/BizFabrik.html?cid=5a#LoginPlace:login");
		driver.findElement(By.xpath("//div[@id='bizCenter']//tr[3]//input")).sendKeys("administrator");
		driver.findElement(By.xpath("//input[@id='LoginDialogpw']")).sendKeys("administrator");
		driver.findElement(By.id("LoginDialogLogin")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@id='MenuMenuEntries']/div[1]/input")).sendKeys("work item");
		Thread.sleep(10000);
		driver.findElement(By.id("MenuMenuAD_Item")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("tAD_ItemMenuNew")).click();
	}
	
	
	
	@Test
	public void workItem(){
	}
	

}
