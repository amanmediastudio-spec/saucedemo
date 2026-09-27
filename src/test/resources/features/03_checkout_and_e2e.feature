@checkout @e2e
Feature: SauceDemo Checkout and End-to-End Workflow

  As an authenticated user
  I want to checkout my cart items and place an order
  So that I can successfully purchase my selected products

  Background:
    Given the user is logged in with standard credentials

  @tc09 @negative @validation
  Scenario: TC09 - Validate mandatory fields on checkout step one
    Given the user adds "Sauce Labs Fleece Jacket" to the cart
    And the user navigates to the cart page
    When the user clicks the checkout button
    And the user clicks continue without entering any checkout information
    Then a checkout error message should say "Error: First Name is required"
    When the user enters checkout first name "John"
    And clicks continue on checkout
    Then a checkout error message should say "Error: Last Name is required"
    When the user enters checkout last name "Doe"
    And clicks continue on checkout
    Then a checkout error message should say "Error: Postal Code is required"

  @tc10 @smoke @e2e
  Scenario: TC10 - Complete end-to-end checkout purchase workflow
    Given the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Fleece Jacket" to the cart
    When the user navigates to the cart page
    And the user clicks the checkout button
    And the user enters checkout information with first name "Jane", last name "Doe", and postal code "10001"
    And clicks continue on checkout
    Then the checkout overview page title should be "Checkout: Overview"
    And the item subtotal should match the sum of item prices
    And the total amount should be equal to subtotal plus tax
    When the user clicks finish to complete the purchase
    Then the order confirmation header should be "Thank you for your order!"
    And the checkout complete title should be "Checkout: Complete!"
    When the user clicks back home
    Then the user should be redirected to the inventory page

  @tc11 @regression @logout
  Scenario: TC11 - Verify user logout via sidebar menu
    When the user opens the sidebar menu and clicks logout
    Then the user should be redirected to the login page
    And the login button should be visible
