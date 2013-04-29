package org.qainfolabs.behaviour.executor;

import org.qainfolabs.behaviour.webdriver.WebDriverHelper;
import org.qainfolabs.behaviour.webdriver.drivers.PropertyWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.PublicKey;


@Configuration
public class ConfigurationClass {


   @Autowired PropertyWebDriver propertyWebDriver;

    @Autowired WebDriverHelper webDriverHelper;


    @Bean
    @Lazy
    @Scope("prototype")
    public PropertyWebDriver getPropertyWebdriver() {
        return new PropertyWebDriver();
    }




    @Bean(name = "helper")
    @Lazy
    @Scope("prototype")
    public WebDriverHelper getWebdriverHelper() {
        return new WebDriverHelper(propertyWebDriver);
    }

//    @Bean
//    @Lazy
//    @Scope("prototype")
//
//    public StepExecutor getStepExecutor() {
//        return new StepExecutor();
//    }

//
//    @Bean
//    @Lazy
//    @Scope("prototype")
//    public ScenarioExecutor scenarioExecutor() {
//        return new ScenarioExecutor();
//    }




}
