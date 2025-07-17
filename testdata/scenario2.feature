Feature: Car Insurance Flow without Registration Number

  Scenario: TC_002_01 - Navigate to Car Insurance Page
    Given I load test data for "TC_002_01"
    And user is on the car insurance page

  Scenario: TC_002_02 - Click without car number
    Given I load test data for "TC_002_02"
    And user is on the car insurance page
    When user clicks on Click here without car number
    Then city selection field should be visible

  Scenario: TC_002_03 - Select city
    Given I load test data for "TC_002_03"
    And user is on the car insurance page
    When user clicks on Click here without car number
    And user selects city from data
    Then brand selection field should be visible

  Scenario: TC_002_04 - Select brand
    Given I load test data for "TC_002_04"
    And user is on the car insurance page
    When user clicks on Click here without car number
    And user selects city from data
    And user selects brand from data
    Then model search input should be visible

  Scenario: TC_002_05 - Select car model
    Given I load test data for "TC_002_05"
    And user is on the car insurance page
    When user clicks on Click here without car number
    And user selects city from data
    And user selects brand from data
    And user selects model from data
    Then fuel type field should be visible

  Scenario: TC_002_06 - Select fuel type
    Given I load test data for "TC_002_06"
    And user is on the car insurance page
    When user clicks on Click here without car number
    And user selects city from data
    And user selects brand from data
    And user selects model from data
    And user selects fuel type from data
    Then variant field should be visible

  Scenario: TC_002_07 - Select variant
    Given I load test data for "TC_002_07"
    And user is on the car insurance page
    When user clicks on Click here without car number
    And user selects city from data
    And user selects brand from data
    And user selects model from data
    And user selects fuel type from data
    And user selects variant from data
    Then form page should be displayed

  Scenario: TC_002_08 - Enter name and invalid phone
    Given I load test data for "TC_002_08"
    And user is on the car insurance page
    When user clicks on Click here without car number
    And user selects city from data
    And user selects brand from data
    And user selects model from data
    And user selects fuel type from data
    And user selects variant from data
    And user enters name and phone from data
    Then invalid phone error message should be visible
