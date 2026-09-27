package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object representing Checkout Complete Page (/checkout-complete.html)
 */
public class CheckoutCompletePage extends BasePage {

    private final By pageTitle = By.cssSelector("span.title");
    private final By completeHeader = By.cssSelector("[data-test='complete-header'], .complete-header");
    private final By completeText = By.cssSelector("[data-test='complete-text'], .complete-text");
    private final By backHomeButton = By.cssSelector("[data-test='back-to-products'], #back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("checkout-complete.html"));
        return getText(pageTitle);
    }

    public String getCompleteHeader() {
        return getText(completeHeader);
    }

    public String getCompleteText() {
        return getText(completeText);
    }

    public void clickBackHome() {
        jsClick(backHomeButton);
    }

    public boolean isOrderComplete() {
        return isDisplayed(completeHeader) && getCompleteHeader().equalsIgnoreCase("Thank you for your order!");
    }
}
