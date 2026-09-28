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
 * Original Source: CheckoutStepTwoPage
 */
public class CheckoutStepTwoPage extends BasePage {

    // Registered SDK Page Elements (Self-Healing Enabled)
    public PageElement pageTitle;
    public PageElement itemNames;
    public PageElement itemPrices;
    public PageElement subtotalLabel;
    public PageElement taxLabel;
    public PageElement totalLabel;
    public PageElement finishButton;
    public PageElement cancelButton;

    public CheckoutStepTwoPage() {
        super("CheckoutStepTwoPage");
    }

    public CheckoutStepTwoPage(String pageName) {
        super(pageName);
    }

    @Override
    protected void initElements() {
        pageTitle = register("pageTitle", "pageTitle", By.cssSelector("span.title"));
        itemNames = register("itemNames", "itemNames", By.cssSelector("[data-test='inventory-item-name']"));
        itemPrices = register("itemPrices", "itemPrices", By.cssSelector("[data-test='inventory-item-price']"));
        subtotalLabel = register("subtotalLabel", "subtotalLabel", By.cssSelector("[data-test='subtotal-label'], .summary_subtotal_label"));
        taxLabel = register("taxLabel", "taxLabel", By.cssSelector("[data-test='tax-label'], .summary_tax_label"));
        totalLabel = register("totalLabel", "totalLabel", By.cssSelector("[data-test='total-label'], .summary_total_label"));
        finishButton = register("finishButton", "finishButton", By.cssSelector("[data-test='finish'], #finish"));
        cancelButton = register("cancelButton", "cancelButton", By.cssSelector("[data-test='cancel'], #cancel"));
    }

    public String getPageTitle() {
        com.automation.utils.WaitUtils.getWait().until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("checkout-step-two.html"));
        return getText(this.pageTitle);
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
            String raw = el.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(raw));
        }
        return prices;
    }

    public double getSubtotal() {
        String text = getText(this.subtotalLabel);
        // Format: "Item total: $39.98"
String number = text.replaceAll("[^0-9.]", "").trim();
        return Double.parseDouble(number);
    }

    public double getTax() {
        String text = getText(this.taxLabel);
        // Format: "Tax: $3.20"
String number = text.replaceAll("[^0-9.]", "").trim();
        return Double.parseDouble(number);
    }

    public double getTotal() {
        String text = getText(this.totalLabel);
        // Format: "Total: $43.18"
String number = text.replaceAll("[^0-9.]", "").trim();
        return Double.parseDouble(number);
    }

    public void clickFinish() {
        org.openqa.selenium.WebElement btn = waitForVisibility(finishButton);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        try {
            waitForClickability(finishButton).click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    public void clickCancel() {
        click(this.cancelButton);
    }

}
