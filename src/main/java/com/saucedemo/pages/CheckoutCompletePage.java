package com.saucedemo.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;

/**
 * Migrated Playwright Page Object strictly compliant with the Platform SDK.
 * Original Source: CheckoutCompletePage
 */
public class CheckoutCompletePage extends PlaywrightBasePage {

    // Registered Playwright Elements
    public PlaywrightPageElement pageTitle;
    public PlaywrightPageElement completeHeader;
    public PlaywrightPageElement completeText;
    public PlaywrightPageElement backHomeButton;

    public CheckoutCompletePage() {
        super("CheckoutCompletePage");
    }

    public CheckoutCompletePage(String pageName) {
        super(pageName);
    }

    @Override
    protected void initElements() {
        pageTitle = register("pageTitle", "pageTitle", "span.title");
        completeHeader = register("completeHeader", "completeHeader", "[data-test='complete-header'], .complete-header");
        completeText = register("completeText", "completeText", "[data-test='complete-text'], .complete-text");
        backHomeButton = register("backHomeButton", "backHomeButton", "[data-test='back-to-products'], #back-to-products");
    }

    public String getPageTitle() {
        // Note: Playwright auto-waits on action, explicit wait omitted
        return getText(pageTitle);
    }

    public String getCompleteHeader() {
        return getText(completeHeader);
    }

    public String getCompleteText() {
        return getText(completeText);
    }

    public void clickBackHome() {
        jsClick(backHomeButton);
    }

    public boolean isOrderComplete() {
        return isDisplayed(completeHeader) && getCompleteHeader().equalsIgnoreCase("Thank you for your order!");
    }

}
