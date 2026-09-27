package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object representing SauceDemo Cart Page (/cart.html)
 */
public class CartPage extends BasePage {

    private final By pageTitle = By.cssSelector("span.title");
    private final By cartItems = By.cssSelector(".cart_item");
    private final By itemNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By checkoutButton = By.cssSelector("[data-test='checkout'], #checkout");
    private final By continueShoppingButton = By.cssSelector("[data-test='continue-shopping'], #continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("cart.html"));
        return getText(pageTitle);
    }

    public List<String> getCartItemNames() {
        if (!isElementPresent(cartItems)) {
            return new ArrayList<>();
        }
        List<WebElement> elements = driver.findElements(itemNames);
        List<String> names = new ArrayList<>();
        for (WebElement el : elements) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public boolean isItemInCart(String productName) {
        return getCartItemNames().contains(productName);
    }

    public void removeItem(String productName) {
        By removeButtonLocator = By.xpath("//div[contains(@class,'cart_item')][.//*[normalize-space()='" + productName + "']]//button[contains(@data-test,'remove') or contains(text(),'Remove')]");
        jsClick(removeButtonLocator);
    }

    public void clickCheckout() {
        jsClick(checkoutButton);
    }

    public void clickContinueShopping() {
        jsClick(continueShoppingButton);
    }
}
