package org.qainfolabs.behaviour.tests;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;


public class TestPhantomTest {
    WebDriver driver;

    @Test
    public void testPhantom() throws MalformedURLException {
        DesiredCapabilities cp = new DesiredCapabilities();
      //  cp.setBrowserName("phantomjs");
//        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), cp);
        driver = new FirefoxDriver();
        driver.get("http://www.etsy.com");
        this.search("Jeans");

        String results = driver.findElement(By.cssSelector(".search-restrictions .count")).getText();
        assertEquals(results, "17,684 items");
    }

    public void search(String term){
        try{
            WebElement search = driver.findElement(By.cssSelector("#search-queqry"));
            search.sendKeys(term);
            search.submit();
        } catch (Exception e){
            System.out.println("An error occurred");
            System.out.println(e);
        }
    }
}
