package com.saucedemo.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;

/**
 * Migrated Playwright Page Object strictly compliant with the Platform SDK.
 * Original Source: LoginPage
 */
public class LoginPage extends PlaywrightBasePage {

    // Registered Playwright Elements
    public PlaywrightPageElement usernameInput;
    public PlaywrightPageElement passwordInput;
    public PlaywrightPageElement loginButton;
    public PlaywrightPageElement errorMessageContainer;
    public PlaywrightPageElement loginLogo;

    public LoginPage() {
        super("LoginPage");
    }

    public LoginPage(String pageName) {
        super(pageName);
    }

    public LoginPage(Object legacyDriver) {
        super("LoginPage", legacyDriver);
    }

    public LoginPage(String pageName, Object legacyDriver) {
        super(pageName, legacyDriver);
    }

    public LoginPage(Object legacyDriver, String pageName) {
        super(legacyDriver, pageName);
    }

    @Override
    protected void initElements() {
        usernameInput = register("usernameInput", "usernameInput", "#user-name");
        passwordInput = register("passwordInput", "passwordInput", "#password");
        loginButton = register("loginButton", "loginButton", "#login-button");
        errorMessageContainer = register("errorMessageContainer", "errorMessageContainer", "[data-test='error']");
        loginLogo = register("loginLogo", "loginLogo", ".login_logo");
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return getText(errorMessageContainer);
    }

    public boolean isErrorMessageDisplayed() {
        return isVisible(errorMessageContainer);
    }

    public boolean isOnLoginPage() {
        try {
            return waitForVisibility(loginButton).isVisible();
        } catch (Exception e) {
            return false;
        }
    }

}
