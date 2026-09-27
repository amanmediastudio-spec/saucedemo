package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object representing SauceDemo Products / Inventory Page (/inventory.html)
 */
public class InventoryPage extends BasePage {

    private final By pageTitle = By.cssSelector("span.title");
    private final By inventoryContainer = By.id("inventory_container");
    private final By inventoryItems = By.cssSelector(".inventory_item");
    private final By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");
    private final By burgerMenuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By resetAppStateLink = By.id("reset_sidebar_link");
    private final By closeMenuButton = By.id("react-burger-cross-btn");
    private final By itemNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By itemPrices = By.cssSelector("[data-test='inventory-item-price']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("inventory.html"));
        return getText(pageTitle);
    }

    public boolean isProductCatalogDisplayed() {
        return isDisplayed(inventoryContainer) && !findElements(inventoryItems).isEmpty();
    }

    public int getProductCount() {
        return findElements(inventoryItems).size();
    }

    public void selectSortOption(String visibleTextOrValue) {
        // Support either visible text like "Price (low to high)" or code "lohi"
        try {
            selectByVisibleText(sortDropdown, visibleTextOrValue);
        } catch (Exception e) {
            selectByValue(sortDropdown, visibleTextOrValue);
        }
    }

    public List<String> getItemNames() {
        List<WebElement> elements = findElements(itemNames);
        List<String> names = new ArrayList<>();
        for (WebElement el : elements) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public List<Double> getItemPrices() {
        List<WebElement> elements = findElements(itemPrices);
        List<Double> prices = new ArrayList<>();
        for (WebElement el : elements) {
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
        return isDisplayed(removeLocator);
    }

    public void clickProductTitle(String productName) {
        By productLink = By.xpath("//*[contains(@class,'inventory_item_name') and normalize-space()='" + productName + "']");
        click(productLink);
    }

    public boolean isCartBadgeDisplayed() {
        try {
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofMillis(500));
            List<WebElement> elements = driver.findElements(cartBadge);
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        }
    }

    public boolean waitForCartBadgeToDisappear() {
        try {
            return wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(cartBadge));
        } catch (Exception e) {
            return !isCartBadgeDisplayed();
        }
    }

    public int getCartBadgeCount() {
        WebElement badge = waitForVisibility(cartBadge);
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
