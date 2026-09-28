package com.saucedemo.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;

/**
 * Migrated Playwright Page Object strictly compliant with the Platform SDK.
 * Original Source: ProductDetailsPage
 */
public class ProductDetailsPage extends PlaywrightBasePage {

    // Registered Playwright Elements
    public PlaywrightPageElement productName;
    public PlaywrightPageElement productDescription;
    public PlaywrightPageElement productPrice;
    public PlaywrightPageElement addToCartButton;
    public PlaywrightPageElement removeButton;
    public PlaywrightPageElement backToProductsButton;
    public PlaywrightPageElement cartBadge;

    public ProductDetailsPage() {
        super("ProductDetailsPage");
    }

    public ProductDetailsPage(String pageName) {
        super(pageName);
    }

    public ProductDetailsPage(Object legacyDriver) {
        super("ProductDetailsPage", legacyDriver);
    }

    public ProductDetailsPage(String pageName, Object legacyDriver) {
        super(pageName, legacyDriver);
    }

    public ProductDetailsPage(Object legacyDriver, String pageName) {
        super(legacyDriver, pageName);
    }

    @Override
    protected void initElements() {
        productName = register("productName", "productName", ".inventory_details_name, [data-test='inventory-item-name']");
        productDescription = register("productDescription", "productDescription", ".inventory_details_desc, [data-test='inventory-item-desc']");
        productPrice = register("productPrice", "productPrice", ".inventory_details_price, [data-test='inventory-item-price']");
        addToCartButton = register("addToCartButton", "addToCartButton", "button[data-test^='add-to-cart']");
        removeButton = register("removeButton", "removeButton", "button[data-test^='remove']");
        backToProductsButton = register("backToProductsButton", "backToProductsButton", "[data-test='back-to-products'], #back-to-products");
        cartBadge = register("cartBadge", "cartBadge", "[data-test='shopping-cart-badge']");
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
        return isVisible(addToCartButton);
    }

    public boolean isRemoveButtonDisplayed() {
        return isVisible(removeButton);
    }

    public boolean isCartBadgeDisplayed() {
        return isVisible(cartBadge);
    }

    public int getCartBadgeCount() {
        if (!isCartBadgeDisplayed()) {
            return 0;
        }
        return Integer.parseInt(getText(cartBadge));
    }

}
