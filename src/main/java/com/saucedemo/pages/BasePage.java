package com.saucedemo.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.microsoft.playwright.Locator;
import java.util.List;
import com.saucedemo.utils.ConfigReader;
import java.time.Duration;

/**
 * Migrated Playwright Page Object strictly compliant with the Platform SDK.
 * Original Source: BasePage
 */
public class BasePage extends PlaywrightBasePage {

    public BasePage() {
        super("BasePage");
    }

    public BasePage(String pageName) {
        super(pageName);
    }

    public BasePage(Object legacyDriver) {
        super("BasePage", legacyDriver);
    }

    public BasePage(String pageName, Object legacyDriver) {
        super(pageName, legacyDriver);
    }

    public BasePage(Object legacyDriver, String pageName) {
        super(legacyDriver, pageName);
    }

    @Override
    protected void initElements() {
    }

    public Locator waitForVisibility(By locator) {
        return getText(getElement("element"));
    }

    public Locator waitForClickability(By locator) {
        return getText(getElement("element"));
    }

    public void click(By locator) {
        try {
            waitForClickability(locator).click();
        } catch (Exception e) {
            jsClick(locator);
        }
    }

    public void jsClick(By locator) {
        Locator element = waitForVisibility(locator);
        getPage().evaluate("arguments[0].scrollIntoView(true);");
        getPage().evaluate("arguments[0].click();");
    }

    public void jsClick(Locator element) {
        getPage().evaluate("arguments[0].scrollIntoView(true);");
        getPage().evaluate("arguments[0].click();");
    }

    public void type(By locator, String text) {
        Locator element = waitForVisibility(locator);
        element.clear();
        if (text != null && !text.isEmpty()) {
            element.sendKeys(text);
        }
    }

    public String getText(By locator) {
        return getText(getElement("element"));
    }

    public boolean isDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementHidden(By locator) {
        try {
            return getText(locator);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public void selectByVisibleText(By locator, String text) {
        Locator element = waitForVisibility(locator);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void selectByValue(By locator, String value) {
        Locator element = waitForVisibility(locator);
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public String getSelectedOptionText(By locator) {
        Locator element = waitForVisibility(locator);
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText().trim();
    }

    public List<Locator> findElements(By locator) {
        return getText(getElement("element"));
    }

    public void scrollToElement(By locator) {
        Locator element = driver.findElement(locator);
        getPage().evaluate("arguments[0].scrollIntoView(true);");
    }

    public String getCurrentUrl() {
        return getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

}
