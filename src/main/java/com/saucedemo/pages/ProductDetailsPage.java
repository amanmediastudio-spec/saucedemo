package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object representing SauceDemo Product Details Page (/inventory-item.html)
 */
public class ProductDetailsPage extends BasePage {

    private final By productName = By.cssSelector(".inventory_details_name, [data-test='inventory-item-name']");
    private final By productDescription = By.cssSelector(".inventory_details_desc, [data-test='inventory-item-desc']");
    private final By productPrice = By.cssSelector(".inventory_details_price, [data-test='inventory-item-price']");
    private final By addToCartButton = By.cssSelector("button[data-test^='add-to-cart-1']");
    private final By removeButton = By.cssSelector("button[data-test^='removed']");
    private final By backToProductsButton = By.cssSelector("[data-test='back-to-products'], #back-to-products");
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductDescription() {
        return getText(productDescription);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public void clickAddToCart() {
        click(addToCartButton);
    }

    public void clickRemove() {
        click(removeButton);
    }

    public void clickBackToProducts() {
        click(backToProductsButton);
    }

    public boolean isAddToCartButtonDisplayed() {
        return isDisplayed(addToCartButton);
    }

    public boolean isRemoveButtonDisplayed() {
        return isDisplayed(removeButton);
    }

    public boolean isCartBadgeDisplayed() {
        return isDisplayed(cartBadge);
    }

    public int getCartBadgeCount() {
        if (!isCartBadgeDisplayed()) {
            return 0;
        }
        return Integer.parseInt(getText(cartBadge));
    }
}
