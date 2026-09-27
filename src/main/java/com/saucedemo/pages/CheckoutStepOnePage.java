package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object representing Checkout Step One - Your Information (/checkout-step-one.html)
 */
public class CheckoutStepOnePage extends BasePage {

    private final By pageTitle = By.cssSelector("span.title");
    private final By firstNameInput = By.cssSelector("[data-test='firstName'], #first-name");
    private final By lastNameInput = By.cssSelector("[data-test='lastName'], #last-name");
    private final By postalCodeInput = By.cssSelector("[data-test='postalCode'], #postal-code");
    private final By continueButton = By.cssSelector("[data-test='continue'], #continue");
    private final By cancelButton = By.cssSelector("[data-test='cancel'], #cancel");
    private final By errorMessageContainer = By.cssSelector("[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public void enterFirstName(String firstName) {
        type(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameInput, lastName);
    }

    public void enterPostalCode(String postalCode) {
        type(postalCodeInput, postalCode);
    }

    public void enterInformation(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    public void clickContinue() {
        waitForClickability(continueButton).click();
    }

    public void clickCancel() {
        jsClick(cancelButton);
    }

    public String getErrorMessage() {
        return getText(errorMessageContainer);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessageContainer);
    }
}
