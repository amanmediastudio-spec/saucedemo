package com.saucedemo.driver;

import com.automation.playwright.PlaywrightBasePage;
import com.saucedemo.utils.ConfigReader;
import java.time.Duration;

/**
 * Migrated Playwright Page Object strictly compliant with the Platform SDK.
 * Original Source: DriverFactory
 */
public class DriverFactory extends PlaywrightBasePage {

    // Custom Project Components
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
        driverThreadLocal = new ThreadLocal<WebDriver>(getPage());
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
        return driver;
    }

    public void quitDriver() {
        // WebDriver instance omitted in Playwright
        // driver null-check omitted in Playwright
    }

}
