# SauceDemo Selenium Java Cucumber Automation Framework

An enterprise-grade, robust, and scalable test automation framework for [SauceDemo (Swag Labs)](https://www.saucedemo.com/) built using **Selenium WebDriver (Java)**, **Cucumber BDD**, and **TestNG**.

---

## 🌟 Framework Highlights

- **BDD with Cucumber 7**: Plain English Gherkin specifications (`.feature` files) easily understood by both technical and business stakeholders.
- **Page Object Model (POM)**: Complete separation of page locators, actions, and test step definitions for maximum maintainability.
- **Selenium 4 Native Driver Management**: Utilizes Selenium Manager built into Selenium 4.22+ to eliminate driver binary dependency and version mismatches.
- **ThreadLocal WebDriver**: Thread-safe driver management supporting concurrent and parallel execution.
- **Explicit Waits & Fallback Handling**: Resilient waiting mechanism with JavaScript-assisted interactions for dynamic React SPA components.
- **Comprehensive Reporting**: Generates interactive HTML and JSON reports (`cucumber-report.html`, Surefire TestNG reports).
- **Automatic Failure Screenshot Capture**: Cucumber scenario hooks capture and attach full-resolution PNG screenshots on any scenario failure.
- **Configurable Environments**: Externalized `config.properties` supporting dynamic CLI overrides (`-Dbrowser=chrome`, `-Dheadless=false`).

---

## 🏗️ Project Architecture

```
selenium-saucedemo-project/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/saucedemo/
│   │           ├── driver/
│   │           │   └── DriverFactory.java          # ThreadLocal WebDriver manager
│   │           ├── pages/
│   │           │   ├── BasePage.java               # Explicit wait & click abstractions
│   │           │   ├── LoginPage.java              # Login page locators & actions
│   │           │   ├── InventoryPage.java          # Product catalog, sorting, cart badge
│   │           │   ├── ProductDetailsPage.java     # Individual product view
│   │           │   ├── CartPage.java               # Shopping cart verification & items
│   │           │   ├── CheckoutStepOnePage.java    # Customer information & validation
│   │           │   ├── CheckoutStepTwoPage.java    # Order summary, pricing & tax check
│   │           │   └── CheckoutCompletePage.java   # Order confirmation & completion
│   │           └── utils/
│   │               └── ConfigReader.java           # Property loader with CLI override
│   └── test/
│       ├── java/
│       │   └── com/saucedemo/
│       │       ├── runners/
│       │       │   └── TestNGCucumberRunner.java   # TestNG Cucumber Runner
│       │       └── stepdefinitions/
│       │           ├── Hooks.java                  # Setup, teardown & screenshot hooks
│       │           ├── LoginSteps.java             # Authentication step definitions
│       │           ├── InventorySteps.java         # Catalog & sorting step definitions
│       │           ├── CartSteps.java              # Cart management step definitions
│       │           └── CheckoutSteps.java          # Checkout & E2E step definitions
│       └── resources/
│           ├── config.properties                   # Browser, timeout, & URL configs
│           ├── testng.xml                          # TestNG execution suite
│           └── features/
│               ├── 01_login.feature                # TC01 - TC04
│               ├── 02_inventory_and_cart.feature   # TC05 - TC08
│               └── 03_checkout_and_e2e.feature     # TC09 - TC11
```

---

## 📋 Standard Test Cases Matrix

| Test ID | Feature Area | Scenario Name | Description | Tags |
|---|---|---|---|---|
| **TC01** | Authentication | Successful login with valid credentials | Logs in with `standard_user` and verifies inventory catalog display | `@smoke`, `@positive` |
| **TC02** | Authentication | Login attempt with locked out account | Validates locked out account message for `locked_out_user` | `@negative` |
| **TC03** | Authentication | Login attempt with invalid password | Validates error banner on incorrect password | `@negative` |
| **TC04** | Authentication | Login validation for empty fields | Tests required field validations for missing username and missing password | `@negative`, `@validation` |
| **TC05** | Inventory | Verify product sorting by price and name | Validates sorting: Price Low-to-High, Price High-to-Low, and Name Z-to-A | `@regression`, `@sorting` |
| **TC06** | Cart Management | Add multiple items to cart & verify badge count | Adds multiple products and asserts dynamic shopping cart badge updates | `@regression`, `@cart` |
| **TC07** | Cart Management | Remove items from inventory and cart pages | Removes products from both inventory view and cart page, verifying counter decrements to 0 | `@regression`, `@cart` |
| **TC08** | Product Details | View product details and add to cart | Navigates to individual product page, validates name/price, and adds to cart | `@regression`, `@product_details` |
| **TC09** | Checkout Validation | Validate mandatory fields on checkout step one | Verifies inline error banners for missing First Name, Last Name, and Postal Code | `@negative`, `@validation` |
| **TC10** | End-to-End | Complete end-to-end checkout purchase workflow | Full purchasing lifecycle: Login -> Add products -> Cart -> Info -> Price + Tax calculation assertion -> Finish -> Confirmation | `@smoke`, `@e2e` |
| **TC11** | Session / Menu | Verify user logout via sidebar menu | Opens animated hamburger menu, triggers logout, and confirms redirect to login screen | `@regression`, `@logout` |

---

## 🚀 Execution Guide

### Prerequisites
- **JDK 17** or higher (tested on Java 21)
- **Apache Maven 3.8+**
- **Google Chrome** or **Microsoft Edge** browser

### Run All Scenarios
```powershell
mvn clean test
```

### Run in Headed (Visible Browser) Mode
```powershell
mvn test -Dheadless=false
```

### Run on a Specific Browser (Edge / Chrome)
```powershell
mvn test -Dbrowser=edge
```

### Run by Cucumber Tags
- Smoke tests:
  ```powershell
  mvn test -Dcucumber.filter.tags="@smoke"
  ```
- Negative validations:
  ```powershell
  mvn test -Dcucumber.filter.tags="@negative"
  ```
- End-to-end tests:
  ```powershell
  mvn test -Dcucumber.filter.tags="@e2e"
  ```

---

## 📊 Test Reports

After test execution, reports are generated automatically at:

1. **Cucumber HTML Report**:
   ```
   target/cucumber-reports/cucumber-report.html
   ```
2. **Cucumber JSON Report**:
   ```
   target/cucumber-reports/cucumber-report.json
   ```
3. **Surefire TestNG Report**:
   ```
   target/surefire-reports/index.html
   ```
   ```
   target/surefire-reports/emailable-report.html
   ```
