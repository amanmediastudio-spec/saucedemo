@inventory @cart
Feature: SauceDemo Product Catalog and Cart Management

  As an authenticated user
  I want to browse products, sort catalog items, view details, and manage my shopping cart
  So that I can select the items I wish to purchase

  Background:
    Given the user is logged in with standard credentials
    And the user is on the inventory page

  @tc05 @regression @sorting
  Scenario: TC05 - Verify product sorting by price and name
    When the user sorts products by "Price (low to high)"
    Then the product prices should be listed in ascending order
    When the user sorts products by "Price (high to low)"
    Then the product prices should be listed in descending order
    When the user sorts products by "Name (Z to A)"
    Then the product names should be listed in reverse alphabetical order

  @tc06 @regression @cart
  Scenario: TC06 - Add multiple items to cart and verify cart badge count
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    Then the cart badge should show count "2"
    When the user navigates to the cart page
    Then the cart should contain the following items:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |

  @tc07 @regression @cart
  Scenario: TC07 - Remove items from cart on inventory page and cart page
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bolt T-Shirt" to the cart
    Then the cart badge should show count "2"
    When the user removes "Sauce Labs Backpack" on the inventory page
    Then the cart badge should show count "1"
    When the user navigates to the cart page
    And the user removes "Sauce Labs Bolt T-Shirt" from the cart page
    Then the cart badge should not be displayed
    And the cart should be empty

  @tc08 @regression @product_details
  Scenario: TC08 - View product details and add to cart from details page
    When the user clicks on the product title "Sauce Labs Onesie"
    Then the product details page should display name "Sauce Labs Onesie"
    And the product details page should display price "$7.99"
    When the user clicks add to cart on the product details page
    Then the cart badge should show count "1"
    When the user clicks the back to products button
    Then the user should be redirected to the inventory page
