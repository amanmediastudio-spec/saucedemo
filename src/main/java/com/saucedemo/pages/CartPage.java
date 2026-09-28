package com.saucedemo.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.microsoft.playwright.Locator;
import java.util.List;
import org.openqa.selenium.By;
import java.util.ArrayList;

/**
 * Migrated Playwright Page Object strictly compliant with the Platform SDK.
 * Original Source: CartPage
 */
public class CartPage extends PlaywrightBasePage {

    // Registered Playwright Elements
    public PlaywrightPageElement pageTitle;
    public PlaywrightPageElement cartItems;
    public PlaywrightPageElement itemNames;
    public PlaywrightPageElement checkoutButton;
    public PlaywrightPageElement continueShoppingButton;

    public CartPage() {
        super("CartPage");
    }

    public CartPage(String pageName) {
        super(pageName);
    }

    public CartPage(Object legacyDriver) {
        super("CartPage", legacyDriver);
    }

    public CartPage(String pageName, Object legacyDriver) {
        super(pageName, legacyDriver);
    }

    public CartPage(Object legacyDriver, String pageName) {
        super(legacyDriver, pageName);
    }

    @Override
    protected void initElements() {
        pageTitle = register("pageTitle", "pageTitle", "span.title");
        cartItems = register("cartItems", "cartItems", ".cart_item");
        itemNames = register("itemNames", "itemNames", "[data-test='inventory-item-name']");
        checkoutButton = register("checkoutButton", "checkoutButton", "[data-test='checkout'], #checkout");
        continueShoppingButton = register("continueShoppingButton", "continueShoppingButton", "[data-test='continue-shopping'], #continue-shopping");
    }

    public String getPageTitle() {
        // Note: Playwright auto-waits on action, explicit wait omitted
        return getText(pageTitle);
    }

    public List<String> getCartItemNames() {
        if (!isElementPresent(cartItems)) {
            return new ArrayList<>();
        }
        List<Locator> elements = findElements(itemNames);
        List<String> names = new ArrayList<>();
        for (Locator el : elements) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public int getItemCount() {
        return count(cartItems);
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
