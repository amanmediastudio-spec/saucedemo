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
 * Original Source: ProductDetailsPage
 */
public class ProductDetailsPage extends BasePage {

    // Registered SDK Page Elements (Self-Healing Enabled)
    public PageElement productName;
    public PageElement productDescription;
    public PageElement productPrice;
    public PageElement addToCartButton;
    public PageElement removeButton;
    public PageElement backToProductsButton;
    public PageElement cartBadge;

    public ProductDetailsPage() {
        super("ProductDetailsPage");
    }

    public ProductDetailsPage(String pageName) {
        super(pageName);
    }

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void initElements() {
        productName = register("productName", "productName", By.cssSelector(".inventory_details_name, [data-test='inventory-item-name']"));
        productDescription = register("productDescription", "productDescription", By.cssSelector(".inventory_details_desc, [data-test='inventory-item-desc']"));
        productPrice = register("productPrice", "productPrice", By.cssSelector(".inventory_details_price, [data-test='inventory-item-price']"));
        addToCartButton = register("addToCartButton", "addToCartButton", By.cssSelector("button[data-test^='add-to-cart']"));
        removeButton = register("removeButton", "removeButton", By.cssSelector("button[data-test^='remove']"));
        backToProductsButton = register("backToProductsButton", "backToProductsButton", By.cssSelector("[data-test='back-to-products'], #back-to-products"));
        cartBadge = register("cartBadge", "cartBadge", By.cssSelector("[data-test='shopping-cart-badge']"));
    }

    public String getProductName() {
        return getText(this.productName);
    }

    public String getProductDescription() {
        return getText(this.productDescription);
    }

    public String getProductPrice() {
        return getText(this.productPrice);
    }

    public void clickAddToCart() {
        click(this.addToCartButton);
    }

    public void clickRemove() {
        click(this.removeButton);
    }

    public void clickBackToProducts() {
        click(this.backToProductsButton);
    }

    public boolean isAddToCartButtonDisplayed() {
        return isDisplayed(this.addToCartButton);
    }

    public boolean isRemoveButtonDisplayed() {
        return isDisplayed(this.removeButton);
    }

    public boolean isCartBadgeDisplayed() {
        return isDisplayed(this.cartBadge);
    }

    public int getCartBadgeCount() {
        if (!isCartBadgeDisplayed()) {
            return 0;
        }
        return Integer.parseInt(getText(cartBadge));
    }

}
