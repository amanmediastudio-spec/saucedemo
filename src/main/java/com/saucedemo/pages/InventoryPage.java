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
 * Original Source: InventoryPage
 */
public class InventoryPage extends BasePage {

    // Registered SDK Page Elements (Self-Healing Enabled)
    public PageElement pageTitle;
    public PageElement inventoryContainer;
    public PageElement inventoryItems;
    public PageElement sortDropdown;
    public PageElement cartBadge;
    public PageElement cartLink;
    public PageElement burgerMenuButton;
    public PageElement logoutLink;
    public PageElement resetAppStateLink;
    public PageElement closeMenuButton;
    public PageElement itemNames;
    public PageElement itemPrices;

    public InventoryPage() {
        super("InventoryPage");
    }

    public InventoryPage(String pageName) {
        super(pageName);
    }

    @Override
    protected void initElements() {
        pageTitle = register("pageTitle", "pageTitle", By.cssSelector("span.title"));
        inventoryContainer = register("inventoryContainer", "inventoryContainer", By.id("inventory_container"));
        inventoryItems = register("inventoryItems", "inventoryItems", By.cssSelector(".inventory_item"));
        sortDropdown = register("sortDropdown", "sortDropdown", By.cssSelector("[data-test='product-sort-container']"));
        cartBadge = register("cartBadge", "cartBadge", By.cssSelector("[data-test='shopping-cart-badge']"));
        cartLink = register("cartLink", "cartLink", By.cssSelector("[data-test='shopping-cart-link']"));
        burgerMenuButton = register("burgerMenuButton", "burgerMenuButton", By.id("react-burger-menu-btn"));
        logoutLink = register("logoutLink", "logoutLink", By.id("logout_sidebar_link"));
        resetAppStateLink = register("resetAppStateLink", "resetAppStateLink", By.id("reset_sidebar_link"));
        closeMenuButton = register("closeMenuButton", "closeMenuButton", By.id("react-burger-cross-btn"));
        itemNames = register("itemNames", "itemNames", By.cssSelector("[data-test='inventory-item-name']"));
        itemPrices = register("itemPrices", "itemPrices", By.cssSelector("[data-test='inventory-item-price']"));
    }

    public String getPageTitle() {
        com.automation.utils.WaitUtils.getWait().until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("inventory.html"));
        return getText(this.pageTitle);
    }

    public boolean isProductCatalogDisplayed() {
        return isDisplayed(inventoryContainer) && !findElements(inventoryItems).isEmpty();
    }

    public int getProductCount() {
        return findElements(inventoryItems).size();
    }

    public void selectSortOption(String visibleTextOrValue) {
        try {
            selectByVisibleText(this.sortDropdown, visibleTextOrValue);
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
            com.automation.driver.DriverManager.getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofMillis(500));
            List<WebElement> elements = findElements(cartBadge);
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
        finally {
            com.automation.driver.DriverManager.getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        }
    }

    public boolean waitForCartBadgeToDisappear() {
        try {
            return com.automation.utils.WaitUtils.getWait().until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(cartBadge));
        } catch (Exception e) {
            return !isCartBadgeDisplayed();
        }
    }

    public int getCartBadgeCount() {
        WebElement badge = waitForVisibility(cartBadge);
        return Integer.parseInt(badge.getText().trim());
    }

    public void clickCart() {
        click(this.cartLink);
    }

    public void openSidebarMenu() {
        click(this.burgerMenuButton);
        waitForVisibility(logoutLink);
    }

    public void clickLogout() {
        openSidebarMenu();
        jsClick(logoutLink);
    }

    public void resetAppState() {
        openSidebarMenu();
        click(this.resetAppStateLink);
        click(this.closeMenuButton);
    }

}
