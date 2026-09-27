package com.saucedemo.driver;

import com.automation.pages.BasePage;
import com.automation.ai.PageElement;
import org.openqa.selenium.By;
import com.automation.components.ThreadLocal<WebDriver>;
import com.saucedemo.utils.ConfigReader;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

/**
 * Migrated Page Object strictly compliant with Platform SDK Core.
 * Original Source: DriverFactory
 */
public class DriverFactory extends BasePage {

    // SDK UI Components
    public ThreadLocal<WebDriver> driverThreadLocal;

    public DriverFactory() {
        super("DriverFactory");
    }

    public DriverFactory(String pageName) {
        super(pageName);
    }

    @Override
    protected void initElements() {

        // Component Initialization
        driverThreadLocal = initComponent(ThreadLocal<WebDriver>.class, "driverThreadLocal", getElement("driverThreadLocal"));
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public WebDriver initDriver() {
        String browser = ConfigReader.getProperty("browser", "chrome").toLowerCase();
        boolean headless = ConfigReader.getBooleanProperty("headless", true);
        int implicitWait = ConfigReader.getIntProperty("implicitWait", 10);
        // WebDriver instance omitted in Playwright
        switch(browser) {
    case "firefox":
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (headless) {
            firefoxOptions.addArguments("-headless");
        }
        driver = new FirefoxDriver(firefoxOptions);
        break;
    case "edge":
        EdgeOptions edgeOptions = new EdgeOptions();
        if (headless) {
            edgeOptions.addArguments("--headless=new");
        }
        edgeOptions.addArguments("--remote-allow-origins=*");
        edgeOptions.addArguments("--disable-gpu");
        edgeOptions.addArguments("--no-sandbox");
        edgeOptions.addArguments("--disable-dev-shm-usage");
        edgeOptions.addArguments("--window-size=1920,1080");
        driver = new EdgeDriver(edgeOptions);
        break;
    case "chrome":
    default:
        ChromeOptions chromeOptions = new ChromeOptions();
        if (headless) {
            chromeOptions.addArguments("--headless=new");
        }
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(chromeOptions);
        break;
}
        com.automation.driver.DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        com.automation.driver.DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        com.automation.driver.DriverManager.getDriver().manage().window().maximize();
        driverThreadLocal.set(driver);
        return driver;
    }

    public void quitDriver() {
        // WebDriver instance omitted in Playwright
        // driver null-check omitted in Playwright
    }

}
