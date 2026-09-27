package com.saucedemo.stepdefinitions;

import com.saucedemo.driver.DriverFactory;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.InventoryPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class CartSteps {

    private CartPage cartPage;
    private InventoryPage inventoryPage;

    private CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }

    private InventoryPage getInventoryPage() {
        if (inventoryPage == null) {
            inventoryPage = new InventoryPage();
        }
        return inventoryPage;
    }

    @When("the user navigates to the cart page")
    public void the_user_navigates_to_the_cart_page() {
        getInventoryPage().clickCart();
        Assert.assertEquals(getCartPage().getPageTitle(), "Your Cart", "Failed to navigate to Cart page.");
    }

    @Then("the cart should contain the following items:")
    public void the_cart_should_contain_the_following_items(DataTable dataTable) {
        List<String> expectedItems = dataTable.asList();
        List<String> actualItems = getCartPage().getCartItemNames();
        for (String expectedItem : expectedItems) {
            Assert.assertTrue(actualItems.contains(expectedItem),
                    "Expected item [" + expectedItem + "] was not found in cart. Cart items: " + actualItems);
        }
    }

    @When("the user removes {string} from the cart page")
    public void the_user_removes_from_the_cart_page(String productName) {
        getCartPage().removeItem(productName);
    }

    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        Assert.assertEquals(getCartPage().getItemCount(), 0, "Cart is not empty.");
    }

    @When("the user clicks the checkout button")
    public void the_user_clicks_the_checkout_button() {
        getCartPage().clickCheckout();
    }
}
