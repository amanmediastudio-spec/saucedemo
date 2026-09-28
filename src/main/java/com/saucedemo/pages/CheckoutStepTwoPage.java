package com.saucedemo.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.microsoft.playwright.Locator;
import java.util.List;
import java.util.ArrayList;

/**
 * Migrated Playwright Page Object strictly compliant with the Platform SDK.
 * Original Source: CheckoutStepTwoPage
 */
public class CheckoutStepTwoPage extends PlaywrightBasePage {

    // Registered Playwright Elements
    public PlaywrightPageElement pageTitle;
    public PlaywrightPageElement itemNames;
    public PlaywrightPageElement itemPrices;
    public PlaywrightPageElement subtotalLabel;
    public PlaywrightPageElement taxLabel;
    public PlaywrightPageElement totalLabel;
    public PlaywrightPageElement finishButton;
    public PlaywrightPageElement cancelButton;

    public CheckoutStepTwoPage() {
        super("CheckoutStepTwoPage");
    }

    public CheckoutStepTwoPage(String pageName) {
        super(pageName);
    }

    public CheckoutStepTwoPage(Object legacyDriver) {
        super("CheckoutStepTwoPage", legacyDriver);
    }

    public CheckoutStepTwoPage(String pageName, Object legacyDriver) {
        super(pageName, legacyDriver);
    }

    public CheckoutStepTwoPage(Object legacyDriver, String pageName) {
        super(legacyDriver, pageName);
    }

    @Override
    protected void initElements() {
        pageTitle = register("pageTitle", "pageTitle", "span.title");
        itemNames = register("itemNames", "itemNames", "[data-test='inventory-item-name']");
        itemPrices = register("itemPrices", "itemPrices", "[data-test='inventory-item-price']");
        subtotalLabel = register("subtotalLabel", "subtotalLabel", "[data-test='subtotal-label'], .summary_subtotal_label");
        taxLabel = register("taxLabel", "taxLabel", "[data-test='tax-label'], .summaryy_tax_label");
        totalLabel = register("totalLabel", "totalLabel", "[data-test='total-label'], .summary_total_label");
        finishButton = register("finishButton", "finishButton", "[data-test='finish'], #finished");
        cancelButton = register("cancelButton", "cancelButton", "[data-test='cancel'], #canceled");
    }

    public String getPageTitle() {
        // Note: Playwright auto-waits on action, explicit wait omitted
        return getText(pageTitle);
    }

    public List<String> getItemNames() {
        List<Locator> elements = findElements(itemNames);
        List<String> names = new ArrayList<>();
        for (Locator el : elements) {
            names.add(el.innerText().trim());
        }
        return names;
    }

    public List<Double> getItemPrices() {
        List<Locator> elements = findElements(itemPrices);
        List<Double> prices = new ArrayList<>();
        for (Locator el : elements) {
            String raw = el.innerText().replace("$", "").trim();
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
        Locator btn = waitForVisibility(finishButton);
        getPage().evaluate("arguments[0].scrollIntoView(true);");
        try {
            waitForClickability(finishButton).click();
        } catch (Exception e) {
            getPage().evaluate("arguments[0].click();");
        }
    }

    public void clickCancel() {
        click(cancelButton);
    }

}
