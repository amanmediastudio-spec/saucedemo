package com.saucedemo.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.microsoft.playwright.Locator;
import java.util.List;
import org.openqa.selenium.By;
import java.util.ArrayList;

/**
 * Migrated Playwright Page Object strictly compliant with the Platform SDK.
 * Original Source: InventoryPage
 */
public class InventoryPage extends PlaywrightBasePage {

    // Registered Playwright Elements
    public PlaywrightPageElement pageTitle;
    public PlaywrightPageElement inventoryContainer;
    public PlaywrightPageElement inventoryItems;
    public PlaywrightPageElement sortDropdown;
    public PlaywrightPageElement cartBadge;
    public PlaywrightPageElement cartLink;
    public PlaywrightPageElement burgerMenuButton;
    public PlaywrightPageElement logoutLink;
    public PlaywrightPageElement resetAppStateLink;
    public PlaywrightPageElement closeMenuButton;
    public PlaywrightPageElement itemNames;
    public PlaywrightPageElement itemPrices;

    public InventoryPage() {
        super("InventoryPage");
    }

    public InventoryPage(String pageName) {
        super(pageName);
    }

    public InventoryPage(Object legacyDriver) {
        super("InventoryPage", legacyDriver);
    }

    public InventoryPage(String pageName, Object legacyDriver) {
        super(pageName, legacyDriver);
    }

    public InventoryPage(Object legacyDriver, String pageName) {
        super(legacyDriver, pageName);
    }

    @Override
    protected void initElements() {
        pageTitle = register("pageTitle", "pageTitle", "span.title");
        inventoryContainer = register("inventoryContainer", "inventoryContainer", "#inventory_container");
        inventoryItems = register("inventoryItems", "inventoryItems", ".inventory_item");
        sortDropdown = register("sortDropdown", "sortDropdown", "[data-test='product-sort-container']");
        cartBadge = register("cartBadge", "cartBadge", "[data-test='shopping-cart-badge']");
        cartLink = register("cartLink", "cartLink", "[data-test='shopping-cart-link']");
        burgerMenuButton = register("burgerMenuButton", "burgerMenuButton", "#react-burger-menu-btn");
        logoutLink = register("logoutLink", "logoutLink", "#logout_sidebar_link");
        resetAppStateLink = register("resetAppStateLink", "resetAppStateLink", "#reset_sidebar_link");
        closeMenuButton = register("closeMenuButton", "closeMenuButton", "#react-burger-cross-btn");
        itemNames = register("itemNames", "itemNames", "[data-test='inventory-item-name']");
        itemPrices = register("itemPrices", "itemPrices", "[data-test='inventory-item-price']");
    }

    public String getPageTitle() {
        // Note: Playwright auto-waits on action, explicit wait omitted
        return getText(pageTitle);
    }

    public boolean isProductCatalogDisplayed() {
        return isDisplayed(inventoryContainer) && !findElements(inventoryItems).isEmpty();
    }

    public int getProductCount() {
        return findElements(inventoryItems).size();
    }

    public void selectSortOption(String visibleTextOrValue) {
        try {
            selectOption(sortDropdown, visibleTextOrValue);
        } catch (Exception e) {
            selectByValue(sortDropdown, visibleTextOrValue);
        }
    }

    public List<String> getItemNames() {
        List<Locator> elements = findElements(itemNames);
        List<String> names = new ArrayList<>();
        for (Locator el : elements) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public List<Double> getItemPrices() {
        List<Locator> elements = findElements(itemPrices);
        List<Double> prices = new ArrayList<>();
        for (Locator el : elements) {
            String rawPrice = el.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(rawPrice));
        }
        return prices;
    }

    public void addProductToCart(String productName) {
        By addToCartLocator = By.xpath("//div[@class='inventory_item'][.//div[normalize-space()='" + productName + "']]//button[contains(@data-test,'add-to-cart') or text()='Add to cart']");
        click(addToCartLocator);
    }

    public void removeProductFromCart(String productName) {
        By removeLocator = By.xpath("//div[@class='inventory_item'][.//div[normalize-space()='" + productName + "']]//button[contains(@data-test,'remove') or text()='Remove']");
        click(removeLocator);
    }

    public boolean isRemoveButtonDisplayed(String productName) {
        By removeLocator = By.xpath("//div[@class='inventory_item'][.//div[normalize-space()='" + productName + "']]//button[contains(@data-test,'remove') or text()='Remove']");
        return isVisible(removeLocator);
    }

    public void clickProductTitle(String productName) {
        By productLink = By.xpath("//*[contains(@class,'inventory_item_name') and normalize-space()='" + productName + "']");
        click(productLink);
    }

    public boolean isCartBadgeDisplayed() {
        try {
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofMillis(500));
            List<Locator> elements = findElements(cartBadge);
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        }
    }

    public boolean waitForCartBadgeToDisappear() {
        try {
            return getText(cartBadge);
        } catch (Exception e) {
            return !isCartBadgeDisplayed();
        }
    }

    public int getCartBadgeCount() {
        Locator badge = waitForVisibility(cartBadge);
        return Integer.parseInt(badge.getText().trim());
    }

    public void clickCart() {
        click(cartLink);
    }

    public void openSidebarMenu() {
        click(burgerMenuButton);
        waitForVisibility(logoutLink);
    }

    public void clickLogout() {
        openSidebarMenu();
        jsClick(logoutLink);
    }

    public void resetAppState() {
        openSidebarMenu();
        click(resetAppStateLink);
        click(closeMenuButton);
    }

}
