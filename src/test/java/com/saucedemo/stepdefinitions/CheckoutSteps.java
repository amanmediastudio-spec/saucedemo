package com.saucedemo.stepdefinitions;

import com.saucedemo.driver.DriverFactory;
import com.saucedemo.pages.CheckoutCompletePage;
import com.saucedemo.pages.CheckoutStepOnePage;
import com.saucedemo.pages.CheckoutStepTwoPage;
import com.saucedemo.pages.InventoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class CheckoutSteps {

    private CheckoutStepOnePage stepOnePage;
    private CheckoutStepTwoPage stepTwoPage;
    private CheckoutCompletePage completePage;
    private InventoryPage inventoryPage;

    private CheckoutStepOnePage getStepOnePage() {
        if (stepOnePage == null) {
            stepOnePage = new CheckoutStepOnePage(DriverFactory.getDriver());
        }
        return stepOnePage;
    }

    private CheckoutStepTwoPage getStepTwoPage() {
        if (stepTwoPage == null) {
            stepTwoPage = new CheckoutStepTwoPage(DriverFactory.getDriver());
        }
        return stepTwoPage;
    }

    private CheckoutCompletePage getCompletePage() {
        if (completePage == null) {
            completePage = new CheckoutCompletePage(DriverFactory.getDriver());
        }
        return completePage;
    }

    private InventoryPage getInventoryPage() {
        if (inventoryPage == null) {
            inventoryPage = new InventoryPage(DriverFactory.getDriver());
        }
        return inventoryPage;
    }

    @When("the user clicks continue without entering any checkout information")
    public void the_user_clicks_continue_without_entering_any_checkout_information() {
        getStepOnePage().clickContinue();
    }

    @Then("a checkout error message should say {string}")
    public void a_checkout_error_message_should_say(String expectedError) {
        Assert.assertTrue(getStepOnePage().isErrorMessageDisplayed(), "Checkout error message was not displayed.");
        String actualError = getStepOnePage().getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError),
                "Error message mismatch. Expected: [" + expectedError + "], but was: [" + actualError + "]");
    }

    @When("the user enters checkout first name {string}")
    public void the_user_enters_checkout_first_name(String firstName) {
        getStepOnePage().enterFirstName(firstName);
    }

    @When("the user enters checkout last name {string}")
    public void the_user_enters_checkout_last_name(String lastName) {
        getStepOnePage().enterLastName(lastName);
    }

    @When("clicks continue on checkout")
    public void clicks_continue_on_checkout() {
        getStepOnePage().clickContinue();
    }

    @When("the user enters checkout information with first name {string}, last name {string}, and postal code {string}")
    public void the_user_enters_checkout_information(String firstName, String lastName, String postalCode) {
        getStepOnePage().enterInformation(firstName, lastName, postalCode);
    }

    @Then("the checkout overview page title should be {string}")
    public void the_checkout_overview_page_title_should_be(String expectedTitle) {
        Assert.assertEquals(getStepTwoPage().getPageTitle(), expectedTitle, "Overview page title mismatch.");
    }

    @Then("the item subtotal should match the sum of item prices")
    public void the_item_subtotal_should_match_the_sum_of_item_prices() {
        List<Double> itemPrices = getStepTwoPage().getItemPrices();
        double sum = 0.0;
        for (double p : itemPrices) {
            sum += p;
        }
        double actualSubtotal = getStepTwoPage().getSubtotal();
        Assert.assertEquals(actualSubtotal, Math.round(sum * 100.0) / 100.0, 0.01, "Calculated item prices sum does not match subtotal.");
    }

    @Then("the total amount should be equal to subtotal plus tax")
    public void the_total_amount_should_be_equal_to_subtotal_plus_tax() {
        double subtotal = getStepTwoPage().getSubtotal();
        double tax = getStepTwoPage().getTax();
        double total = getStepTwoPage().getTotal();
        double expectedTotal = Math.round((subtotal + tax) * 100.0) / 100.0;
        Assert.assertEquals(total, expectedTotal, 0.01, "Total does not match subtotal + tax.");
    }

    @When("the user clicks finish to complete the purchase")
    public void the_user_clicks_finish_to_complete_the_purchase() {
        getStepTwoPage().clickFinish();
    }

    @Then("the order confirmation header should be {string}")
    public void the_order_confirmation_header_should_be(String expectedHeader) {
        Assert.assertEquals(getCompletePage().getCompleteHeader(), expectedHeader, "Order complete header mismatch.");
    }

    @Then("the checkout complete title should be {string}")
    public void the_checkout_complete_title_should_be(String expectedTitle) {
        Assert.assertEquals(getCompletePage().getPageTitle(), expectedTitle, "Checkout complete title mismatch.");
    }

    @When("the user clicks back home")
    public void the_user_clicks_back_home() {
        getCompletePage().clickBackHome();
    }
}
