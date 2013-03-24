package org.qainfolabs.tests.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static junit.framework.Assert.assertEquals;

/**
 * Simple {@link RemoteWebDriver} test that demonstrates how to run your Selenium tests with <a href="http://ondemand.saucelabs.com/ondemand">Sauce OnDemand</a>.
 * *
 * @author Ross Rowe
 */
public class WebDriverTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabillities = DesiredCapabilities.firefox();
//        capabillities.setCapability("version", "5.1");
        capabillities.setCapability("platform", Platform.WINDOWS);
        this.driver = new RemoteWebDriver(
                new URL("http://192.168.0.149:4444/wd/hub"),
                capabillities);
    }

    @Test
    public void basic() throws Exception {
        driver.get("http://m.accorto.com:8080/mobile/");
        driver.findElement(By.id("username")).sendKeys("pankaj@gmail.com");
        driver.findElement(By.id("password")).sendKeys("passwordHellOWorld");
        driver.findElement(By.id("login")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}