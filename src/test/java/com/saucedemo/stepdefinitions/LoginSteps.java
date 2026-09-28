package com.saucedemo.stepdefinitions;

import com.saucedemo.driver.DriverFactory;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    private LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    private InventoryPage getInventoryPage() {
        if (inventoryPage == null) {
            inventoryPage = new InventoryPage();
        }
        return inventoryPage;
    }

    @Given("the user is on the SauceDemo login page")
    public void the_user_is_on_the_saucedemo_login_page() {
        Assert.assertTrue(getLoginPage().isOnLoginPage(), "User is not on the SauceDemo login page.");
    }

    @Given("the user is logged in with username {string} and password {string}")
    public void the_user_is_logged_in_with_username_and_password(String username, String password) {
        getLoginPage().login(username, password);
        Assert.assertEquals(getInventoryPage().getPageTitle(), "Products", "Failed to navigate to Inventory page after login.");
    }

    @Given("the user is logged in with standard credentials")
    public void the_user_is_logged_in_with_standard_credentials() {
        String username = com.saucedemo.utils.ConfigReader.getStandardUsername();
        String password = com.saucedemo.utils.ConfigReader.getPassword();
        getLoginPage().login(username, password);
        Assert.assertEquals(getInventoryPage().getPageTitle(), "Products", "Failed to navigate to Inventory page after login.");
    }

    @When("the user enters standard user credentials")
    public void the_user_enters_standard_user_credentials() {
        getLoginPage().enterUsername(com.saucedemo.utils.ConfigReader.getStandardUsername());
        getLoginPage().enterPassword(com.saucedemo.utils.ConfigReader.getPassword());
    }

    @When("the user enters locked out user credentials")
    public void the_user_enters_locked_out_user_credentials() {
        getLoginPage().enterUsername(com.saucedemo.utils.ConfigReader.getLockedOutUsername());
        getLoginPage().enterPassword(com.saucedemo.utils.ConfigReader.getPassword());
    }

    @When("the user enters standard username and invalid password")
    public void the_user_enters_standard_username_and_invalid_password() {
        getLoginPage().enterUsername(com.saucedemo.utils.ConfigReader.getStandardUsername());
        getLoginPage().enterPassword(com.saucedemo.utils.ConfigReader.getInvalidPassword());
    }

    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) {
        getLoginPage().enterUsername(username);
        getLoginPage().enterPassword(password);
    }

    @When("clicks on the login button")
    public void clicks_on_the_login_button() {
        getLoginPage().clickLogin();
    }

    @Then("the user should be redirected to the inventory page")
    public void the_user_should_be_redirected_to_the_inventory_page() {
        new org.openqa.selenium.support.ui.WebDriverWait(DriverFactory.getDriver(), java.time.Duration.ofSeconds(10))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("inventory.html"));
        String currentUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "URL does not contain 'inventory.html'. Current URL: " + currentUrl);
    }

    @Then("the page header title should be {string}")
    public void the_page_header_title_should_be(String expectedTitle) {
        Assert.assertEquals(getInventoryPage().getPageTitle(), expectedTitle, "Page title mismatch.");
    }

    @Then("the product catalog should be displayed")
    public void the_product_catalog_should_be_displayed() {
        Assert.assertTrue(getInventoryPage().isProductCatalogDisplayed(), "Product catalog is not visible.");
        Assert.assertTrue(getInventoryPage().getProductCount() > 0, "No products found in catalog.");
    }

    @Then("a login error message should be displayed saying {string}")
    public void a_login_error_message_should_be_displayed_saying(String expectedError) {
        Assert.assertTrue(getLoginPage().isErrorMessageDisplayed(), "Error message was not displayed.");
        String actualError = getLoginPage().getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError),
                "Error message mismatch. Expected to contain: [" + expectedError + "], but was: [" + actualError + "]");
    }

    @Then("the user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        Assert.assertTrue(getLoginPage().isOnLoginPage(), "User is not on the login page after logout.");
    }

    @Then("the login button should be visible")
    public void the_login_button_should_be_visible() {
        Assert.assertTrue(getLoginPage().isOnLoginPage(), "Login button is not displayed.");
    }
}
