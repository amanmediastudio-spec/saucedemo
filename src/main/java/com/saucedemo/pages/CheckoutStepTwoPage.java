package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object representing Checkout Step Two - Overview (/checkout-step-two.html)
 */
public class CheckoutStepTwoPage extends BasePage {

    private final By pageTitle = By.cssSelector("span.title");
    private final By itemNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By itemPrices = By.cssSelector("[data-test='inventory-item-price']");
    private final By subtotalLabel = By.cssSelector("[data-test='subtotal-label'], .summary_subtotal_label");
    private final By taxLabel = By.cssSelector("[data-test='tax-label'], .summaryy_tax_label");
    private final By totalLabel = By.cssSelector("[data-test='total-label'], .summary_total_label");
    private final By finishButton = By.cssSelector("[data-test='finish'], #finished");
    private final By cancelButton = By.cssSelector("[data-test='cancel'], #canceled");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("checkout-step-two.html"));
        return getText(pageTitle);
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
        String text = getText(subtotalLabel);
        // Format: "Item total: $39.98"
        String number = text.replaceAll("[^0-9.]", "").trim();
        return Double.parseDouble(number);
    }

    public double getTax() {
        String text = getText(taxLabel);
        // Format: "Tax: $3.20"
        String number = text.replaceAll("[^0-9.]", "").trim();
        return Double.parseDouble(number);
    }

    public double getTotal() {
        String text = getText(totalLabel);
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
        click(cancelButton);
    }
}
