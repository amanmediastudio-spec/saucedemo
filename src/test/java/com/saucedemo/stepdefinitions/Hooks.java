package com.saucedemo.stepdefinitions;

import com.automation.driver.DriverManager;

import com.saucedemo.driver.DriverFactory;
import com.saucedemo.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Cucumber lifecycle hooks for WebDriver initialization, screenshot on failure, and teardown.
 */
public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        WebDriver driver = com.automation.driver.DriverManager.getDriver();
        String baseUrl = ConfigReader.getProperty("baseUrl", "https://www.saucedemo.com");
        driver.get(baseUrl);
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = com.automation.driver.DriverManager.getDriver();
        if (driver != null) {
            try {
                if (scenario.isFailed()) {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", scenario.getName() + " - Failure Screenshot");
                }
            } catch (Exception e) {
                System.err.println("Could not capture screenshot: " + e.getMessage());
            } finally {
                // Driver teardown & video upload managed by Platform SDK Core SeleniumHooks
            // DriverFactory.quitDriver();
            }
        }
    }
}
