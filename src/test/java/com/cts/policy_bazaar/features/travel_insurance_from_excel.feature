Feature: Travel Insurance Functionality

  Scenario: TC_001_01 - Validate accessing the travel insurance page
    Given I load test data for "TC_001_01"
    And I am on the home page
    When I click on the Travel Insurance link
    Then I should be on the travel insurance page

  Scenario: TC_001_02 - Validate selecting destination
    Given I load test data for "TC_001_02"
    And I am on the travel insurance page
    When I enter destination from data
    Then the selected destination should be correct

  Scenario: TC_001_03 - Validate selecting travel dates
    Given I load test data for "TC_001_03"
    And I am on the travel insurance page
    When I select travel dates from data
    Then the selected start date should be correct

  Scenario: TC_001_04 - Validate selecting 2 travellers
    Given I load test data for "TC_001_04"
    And I am on the travel insurance page
    When I select traveller ages from data
    And I submit the traveller details
    Then I should see traveller message

  Scenario: TC_001_05 - Validate viewing plans
    Given I load test data for "TC_001_05"
    And I am on the travel insurance page
    When I enter destination from data
    And I select travel dates from data
    And I select traveller ages from data
    And I submit the traveller details
    And I click on view plans
    Then I should be on the plans page

  Scenario: TC_001_06 - Validate selecting student plans
    Given I load test data for "TC_001_06"
    And I am on the travel insurance page
    When I enter destination from data
    And I select travel dates from data
    And I select traveller ages from data
    And I submit the traveller details
    And I click on view plans
    And I filter by student plans from data
    Then student plans should be displayed

  Scenario: TC_001_07 - Validate sorting plans from low to high
    Given I load test data for "TC_001_07"
    And I am on the travel insurance page
    When I enter destination from data
    And I select travel dates from data
    And I select traveller ages from data
    And I submit the traveller details
    And I click on view plans
    And I filter by student plans from data
    And I sort plans from low to high
    Then plans should be sorted in ascending order

  Scenario: TC_001_08 - Validate getting top 3 plans
    Given I load test data for "TC_001_08"
    And I am on the travel insurance page
    When I enter destination from data
    And I select travel dates from data
    And I select traveller ages from data
    And I submit the traveller details
    And I click on view plans
    And I filter by student plans from data
    And I sort plans from low to high
    And I fetch top 3 plans
    Then I should get company names and insurance amounts

  Scenario: TC_001_09 - Validate no traveller selected shows error
    Given I load test data for "TC_001_09"
    And I am on the travel insurance page
    When I enter destination from data
    And I select travel dates from data
    And I click cut button
    And I click on view plans
    Then I should see error message from data

  Scenario: TC_001_10 - Validate invalid country shows no result
    Given I load test data for "TC_001_10"
    And I am on the travel insurance page
    When I enter destination from data
    Then I should see error message of country from data

  Scenario: TC_001_11 - Validate not selecting date shows error
    Given I load test data for "TC_001_11"
    And I am on the travel insurance page
    When I enter destination from data
    And I select traveller ages from data
    And I submit the traveller details
    And I click cut button
    And I click on view plans
    Then I should see error message from data