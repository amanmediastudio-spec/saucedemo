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
 * Original Source: LoginPage
 */
public class LoginPage extends BasePage {

    // Registered SDK Page Elements (Self-Healing Enabled)
    public PageElement usernameInput;
    public PageElement passwordInput;
    public PageElement loginButton;
    public PageElement errorMessageContainer;
    public PageElement loginLogo;

    public LoginPage() {
        super("LoginPage");
    }

    public LoginPage(String pageName) {
        super(pageName);
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void initElements() {
        usernameInput = register("usernameInput", "usernameInput", By.id("user-name"));
        passwordInput = register("passwordInput", "passwordInput", By.id("password"));
        loginButton = register("loginButton", "loginButton", By.id("login-button"));
        errorMessageContainer = register("errorMessageContainer", "errorMessageContainer", By.cssSelector("[data-test='error']"));
        loginLogo = register("loginLogo", "loginLogo", By.cssSelector(".login_logo"));
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLogin() {
        click(this.loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return getText(this.errorMessageContainer);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(this.errorMessageContainer);
    }

    public boolean isOnLoginPage() {
        try {
            return waitForVisibility(loginButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
