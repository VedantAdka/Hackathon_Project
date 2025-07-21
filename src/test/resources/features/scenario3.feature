Feature: Health Insurance Comparison

  Scenario: Validate visibility and interaction with Insurance Products and Health Insurance
    Given I am on the home page
    When I hover over insurance products
    Then insurance products menu should be enabled
    Then health insurance link should be enabled

  Scenario: TC_003_04 - Validate redirection to Health Insurance page
    Given I load test data for "TC_003_04"
    And I am on the home page
    When I hover over insurance products
    And I click on health insurance
    Then I should be on the health insurance page with data from excel

  Scenario: Retrieve and store health insurance plan details
    Given I am on the home page
    When I hover over insurance products
    And I click on health insurance
    And I close the footer popup
    And I click on View More Plans
    And I click on all View More buttons of listed plans
    Then I fetch insurance names, cover amounts and start amounts
    And I store the plan details to Excel