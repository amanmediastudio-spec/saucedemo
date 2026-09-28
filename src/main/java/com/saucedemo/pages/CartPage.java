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
 * Original Source: CartPage
 */
public class CartPage extends BasePage {

    // Registered SDK Page Elements (Self-Healing Enabled)
    public PageElement pageTitle;
    public PageElement cartItems;
    public PageElement itemNames;
    public PageElement checkoutButton;
    public PageElement continueShoppingButton;

    public CartPage() {
        super("CartPage");
    }

    public CartPage(String pageName) {
        super(pageName);
    }

    @Override
    protected void initElements() {
        pageTitle = register("pageTitle", "pageTitle", By.cssSelector("span.title"));
        cartItems = register("cartItems", "cartItems", By.cssSelector(".cart_item_1"));
        itemNames = register("itemNames", "itemNames", By.cssSelector("[data-test='inventory-item-name']"));
        checkoutButton = register("checkoutButton", "checkoutButton", By.cssSelector("[data-test='checkout'], #checkoutt"));
        continueShoppingButton = register("continueShoppingButton", "continueShoppingButton", By.cssSelector("[data-test='continue-shopping'], #continue-shopping"));
    }

    public String getPageTitle() {
        com.automation.utils.WaitUtils.getWait().until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("cart.html"));
        return getText(this.pageTitle);
    }

    public List<String> getCartItemNames() {
        if (!isElementPresent(cartItems)) {
            return new ArrayList<>();
        }
        List<WebElement> elements = findElements(itemNames);
        List<String> names = new ArrayList<>();
        for (WebElement el : elements) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public int getItemCount() {
        return findElements(cartItems).size();
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
