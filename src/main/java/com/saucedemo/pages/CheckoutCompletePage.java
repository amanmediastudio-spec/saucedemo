package com.saucedemo.pages;

import com.automation.pages.BasePage;
import com.automation.ai.PageElement;
import org.openqa.selenium.By;

/**
 * Migrated Page Object strictly compliant with Platform SDK Core.
 * Original Source: CheckoutCompletePage
 */
public class CheckoutCompletePage extends BasePage {

    // Registered SDK Page Elements (Self-Healing Enabled)
    public PageElement pageTitle;
    public PageElement completeHeader;
    public PageElement completeText;
    public PageElement backHomeButton;

    public CheckoutCompletePage() {
        super("CheckoutCompletePage");
    }

    public CheckoutCompletePage(String pageName) {
        super(pageName);
    }

    @Override
    protected void initElements() {
        pageTitle = register("pageTitle", "pageTitle", By.cssSelector("span.title"));
        completeHeader = register("completeHeader", "completeHeader", By.cssSelector("[data-test='complete-header'], .complete-header"));
        completeText = register("completeText", "completeText", By.cssSelector("[data-test='complete-text'], .complete-text"));
        backHomeButton = register("backHomeButton", "backHomeButton", By.cssSelector("[data-test='back-to-products'], #back-to-products"));
    }

    public String getPageTitle() {
        // Note: Playwright auto-waits on action, explicit wait omitted
        return getText(this.pageTitle);
    }

    public String getCompleteHeader() {
        return getText(this.completeHeader);
    }

    public String getCompleteText() {
        return getText(this.completeText);
    }

    public void clickBackHome() {
        jsClick(backHomeButton);
    }

    public boolean isOrderComplete() {
        return isDisplayed(completeHeader) && getCompleteHeader().equalsIgnoreCase("Thank you for your order!");
    }

}
