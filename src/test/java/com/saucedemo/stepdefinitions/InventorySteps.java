package com.saucedemo.stepdefinitions;

import com.saucedemo.driver.DriverFactory;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.ProductDetailsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventorySteps {

    private InventoryPage inventoryPage;
    private ProductDetailsPage productDetailsPage;

    private InventoryPage getInventoryPage() {
        if (inventoryPage == null) {
            inventoryPage = new InventoryPage(DriverFactory.getDriver());
        }
        return inventoryPage;
    }

    private ProductDetailsPage getProductDetailsPage() {
        if (productDetailsPage == null) {
            productDetailsPage = new ProductDetailsPage(DriverFactory.getDriver());
        }
        return productDetailsPage;
    }

    @Given("the user is on the inventory page")
    public void the_user_is_on_the_inventory_page() {
        Assert.assertEquals(getInventoryPage().getPageTitle(), "Products", "Not on inventory page.");
    }

    @When("the user sorts products by {string}")
    public void the_user_sorts_products_by(String sortOption) {
        getInventoryPage().selectSortOption(sortOption);
    }

    @Then("the product prices should be listed in ascending order")
    public void the_product_prices_should_be_listed_in_ascending_order() {
        List<Double> actualPrices = getInventoryPage().getItemPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);
        Assert.assertEquals(actualPrices, expectedPrices, "Product prices are not sorted in ascending order.");
    }

    @Then("the product prices should be listed in descending order")
    public void the_product_prices_should_be_listed_in_descending_order() {
        List<Double> actualPrices = getInventoryPage().getItemPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());
        Assert.assertEquals(actualPrices, expectedPrices, "Product prices are not sorted in descending order.");
    }

    @Then("the product names should be listed in reverse alphabetical order")
    public void the_product_names_should_be_listed_in_reverse_alphabetical_order() {
        List<String> actualNames = getInventoryPage().getItemNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(Collections.reverseOrder());
        Assert.assertEquals(actualNames, expectedNames, "Product names are not sorted in reverse alphabetical order (Z to A).");
    }

    @When("the user adds {string} to the cart")
    public void the_user_adds_to_the_cart(String productName) {
        getInventoryPage().addProductToCart(productName);
    }

    @When("the user removes {string} on the inventory page")
    public void the_user_removes_on_the_inventory_page(String productName) {
        getInventoryPage().removeProductFromCart(productName);
    }

    @Then("the cart badge should show count {string}")
    public void the_cart_badge_should_show_count(String expectedCount) {
        int expected = Integer.parseInt(expectedCount);
        Assert.assertEquals(getInventoryPage().getCartBadgeCount(), expected, "Cart badge count mismatch.");
    }

    @Then("the cart badge should not be displayed")
    public void the_cart_badge_should_not_be_displayed() {
        Assert.assertTrue(getInventoryPage().waitForCartBadgeToDisappear(), "Cart badge is still displayed when it should be hidden.");
    }

    @When("the user clicks on the product title {string}")
    public void the_user_clicks_on_the_product_title(String productName) {
        getInventoryPage().clickProductTitle(productName);
    }

    @Then("the product details page should display name {string}")
    public void the_product_details_page_should_display_name(String expectedName) {
        Assert.assertEquals(getProductDetailsPage().getProductName(), expectedName, "Product details name mismatch.");
    }

    @Then("the product details page should display price {string}")
    public void the_product_details_page_should_display_price(String expectedPrice) {
        Assert.assertEquals(getProductDetailsPage().getProductPrice(), expectedPrice, "Product details price mismatch.");
    }

    @When("the user clicks add to cart on the product details page")
    public void the_user_clicks_add_to_cart_on_the_product_details_page() {
        getProductDetailsPage().clickAddToCart();
    }

    @When("the user clicks the back to products button")
    public void the_user_clicks_the_back_to_products_button() {
        getProductDetailsPage().clickBackToProducts();
    }

    @When("the user opens the sidebar menu and clicks logout")
    public void the_user_opens_the_sidebar_menu_and_clicks_logout() {
        getInventoryPage().clickLogout();
    }
}
