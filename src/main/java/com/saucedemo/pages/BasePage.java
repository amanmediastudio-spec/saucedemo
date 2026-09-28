package com.saucedemo.pages;

import com.automation.playwright.PlaywrightBasePage;

/**
 * Base Page offering reusable abstractions, delegating to AI Automation Framework Core SDK PlaywrightBasePage.
 */
public abstract class BasePage extends PlaywrightBasePage {

    public BasePage() {
        super();
    }

    public BasePage(String pageName) {
        super(pageName);
    }

    public BasePage(Object legacyDriver) {
        super(legacyDriver);
    }

    public BasePage(String pageName, Object legacyDriver) {
        super(pageName, legacyDriver);
    }

    public BasePage(Object legacyDriver, String pageName) {
        super(legacyDriver, pageName);
    }

    @Override
    protected void initElements() {
        // Elements are registered dynamically or by subclasses
    }
}
