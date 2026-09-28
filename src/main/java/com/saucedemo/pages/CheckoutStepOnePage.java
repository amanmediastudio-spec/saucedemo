package com.saucedemo.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;

/**
 * Migrated Playwright Page Object strictly compliant with the Platform SDK.
 * Original Source: CheckoutStepOnePage
 */
public class CheckoutStepOnePage extends PlaywrightBasePage {

    // Registered Playwright Elements
    public PlaywrightPageElement pageTitle;
    public PlaywrightPageElement firstNameInput;
    public PlaywrightPageElement lastNameInput;
    public PlaywrightPageElement postalCodeInput;
    public PlaywrightPageElement continueButton;
    public PlaywrightPageElement cancelButton;
    public PlaywrightPageElement errorMessageContainer;

    public CheckoutStepOnePage() {
        super("CheckoutStepOnePage");
    }

    public CheckoutStepOnePage(String pageName) {
        super(pageName);
    }

    public CheckoutStepOnePage(Object legacyDriver) {
        super("CheckoutStepOnePage", legacyDriver);
    }

    public CheckoutStepOnePage(String pageName, Object legacyDriver) {
        super(pageName, legacyDriver);
    }

    public CheckoutStepOnePage(Object legacyDriver, String pageName) {
        super(legacyDriver, pageName);
    }

    @Override
    protected void initElements() {
        pageTitle = register("pageTitle", "pageTitle", "span.title");
        firstNameInput = register("firstNameInput", "firstNameInput", "[data-test='firstName'], #first-name");
        lastNameInput = register("lastNameInput", "lastNameInput", "[data-test='lastName'], #l-name");
        postalCodeInput = register("postalCodeInput", "postalCodeInput", "[data-test='postalCode'], #postal-code-1");
        continueButton = register("continueButton", "continueButton", "[data-test='continue'], #continue");
        cancelButton = register("cancelButton", "cancelButton", "[data-test='cancel'], #canceel");
        errorMessageContainer = register("errorMessageContainer", "errorMessageContainer", "[data-test='error']");
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
        return isVisible(errorMessageContainer);
    }

}
