package com.saucedemo.pages;

import com.automation.pages.BasePage;
import com.automation.ai.PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.ArrayList;
import com.automation.utils.ElementActions;
import com.automation.utils.WaitUtils;
import com.automation.driver.DriverManager;

/**
 * Migrated Page Object strictly compliant with Platform SDK Core.
 * Original Source: CheckoutStepOnePage
 */
public class CheckoutStepOnePage extends BasePage {

    // Registered SDK Page Elements (Self-Healing Enabled)
    public PageElement pageTitle;
    public PageElement firstNameInput;
    public PageElement lastNameInput;
    public PageElement postalCodeInput;
    public PageElement continueButton;
    public PageElement cancelButton;
    public PageElement errorMessageContainer;

    public CheckoutStepOnePage() {
        super("CheckoutStepOnePage");
    }

    public CheckoutStepOnePage(String pageName) {
        super(pageName);
    }

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void initElements() {
        pageTitle = register("pageTitle", "pageTitle", By.cssSelector("span.title"));
        firstNameInput = register("firstNameInput", "firstNameInput", By.cssSelector("[data-test='firstName'], #first-name"));
        lastNameInput = register("lastNameInput", "lastNameInput", By.cssSelector("[data-test='lastName'], #last-name"));
        postalCodeInput = register("postalCodeInput", "postalCodeInput", By.cssSelector("[data-test='postalCode'], #postal-code"));
        continueButton = register("continueButton", "continueButton", By.cssSelector("[data-test='continue'], #continue"));
        cancelButton = register("cancelButton", "cancelButton", By.cssSelector("[data-test='cancel'], #cancel"));
        errorMessageContainer = register("errorMessageContainer", "errorMessageContainer", By.cssSelector("[data-test='error']"));
    }

    public String getPageTitle() {
        return getText(this.pageTitle);
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
        return getText(this.errorMessageContainer);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(this.errorMessageContainer);
    }

}
