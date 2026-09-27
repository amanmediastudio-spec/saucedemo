package com.saucedemo.pages;

import com.automation.pages.BasePage;
import com.automation.ai.PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

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
        // Note: Playwright auto-waits on action, explicit wait omitted
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
            ElementActions.selectByVisibleText(getEffectiveBy(this.sortDropdown), visibleTextOrValue);
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
        click(getElement("addToCartLocator"));
    }

    public void removeProductFromCart(String productName) {
        By removeLocator = By.xpath("//div[@class='inventory_item'][.//div[normalize-space()='" + productName + "']]//button[contains(@data-test,'remove') or text()='Remove']");
        click(getElement("removeLocator"));
    }

    public boolean isRemoveButtonDisplayed(String productName) {
        By removeLocator = By.xpath("//div[@class='inventory_item'][.//div[normalize-space()='" + productName + "']]//button[contains(@data-test,'remove') or text()='Remove']");
        return isDisplayed(getElement("removeLocator"));
    }

    public void clickProductTitle(String productName) {
        By productLink = By.xpath("//*[contains(@class,'inventory_item_name') and normalize-space()='" + productName + "']");
        click(getElement("productLink"));
    }

    public boolean isCartBadgeDisplayed() {
        try {
            com.automation.driver.DriverManager.getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofMillis(500));
            List<Locator> elements = findElements(cartBadge);
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
            return wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(cartBadge));
        } catch (Exception e) {
            return !isCartBadgeDisplayed();
        }
    }

    public int getCartBadgeCount() {
        Locator badge = waitForVisibility(cartBadge);
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
