@login
Feature: SauceDemo Authentication

  As a user of SauceDemo
  I want to be able to authenticate securely
  So that I can access the product catalog or receive appropriate error notifications

  Background:
    Given the user is on the SauceDemo login page

  @tc01 @smoke @positive
  Scenario: TC01 - Successful login with valid credentials
    When the user enters standard user credentials
    And clicks on the login button
    Then the user should be redirected to the inventory page
    And the page header title should be "Products"
    And the product catalog should be displayed

  @tc02 @negative
  Scenario: TC02 - Login attempt with locked out account
    When the user enters locked out user credentials
    And clicks on the login button
    Then a login error message should be displayed saying "Epic sadface: Sorry, this user has been locked out."

  @tc03 @negative
  Scenario: TC03 - Login attempt with invalid password
    When the user enters standard username and invalid password
    And clicks on the login button
    Then a login error message should be displayed saying "Epic sadface: Username and password do not match any user in this service"

  @tc04 @negative @validation
  Scenario Outline: TC04 - Login validation for empty fields
    When the user enters username "<username>" and password "<password>"
    And clicks on the login button
    Then a login error message should be displayed saying "<expected_error>"

    Examples:
      | username      | password     | expected_error                   |
      |               |              | Epic sadface: Username is required |
      | standard_user |              | Epic sadface: Password is required |
