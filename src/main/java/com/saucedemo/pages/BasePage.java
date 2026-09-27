package com.saucedemo.pages;

import com.automation.pages.BasePage;
import com.automation.ai.PageElement;
import org.openqa.selenium.By;
import com.automation.components.WebDriverWait;
import com.saucedemo.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;

/**
 * Migrated Page Object strictly compliant with Platform SDK Core.
 * Original Source: BasePage
 */
public class BasePage extends BasePage {

    // SDK UI Components
    public WebDriverWait wait;

    public BasePage() {
        super("BasePage");
    }

    public BasePage(String pageName) {
        super(pageName);
    }

    @Override
    protected void initElements() {

        // Component Initialization
        wait = initComponent(WebDriverWait.class, "wait", getElement("wait"));
    }

    public Locator waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public Locator waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
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
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void jsClick(Locator element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void type(By locator, String text) {
        Locator element = waitForVisibility(locator);
        element.clear();
        if (text != null && !text.isEmpty()) {
            element.sendKeys(text);
        }
    }

    public String getText(By locator) {
        return wait.ignoring(org.openqa.selenium.StaleElementReferenceException.class).until(d -> {
    try {
        WebElement el = d.findElement(locator);
        if (el.isDisplayed()) {
            String t = el.getText();
            return t != null ? t.trim() : "";
        }
        return null;
    } catch (org.openqa.selenium.StaleElementReferenceException e) {
        return null;
    }
});
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
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        return !com.automation.driver.DriverManager.getDriver().findElements(locator).isEmpty();
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
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void scrollToElement(By locator) {
        Locator element = com.automation.driver.DriverManager.getDriver().findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getCurrentUrl() {
        return getCurrentUrl();
    }

    public String getPageSource() {
        return com.automation.driver.DriverManager.getDriver().getPageSource();
    }

}
