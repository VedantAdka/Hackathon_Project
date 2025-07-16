Feature: Travel Insurance Functionality

  Scenario: Validate accessing the travel insurance page
    Given I am on the home page
    When I click on the Travel Insurance link
    Then I should be on the travel insurance page

  Scenario: Validate selecting destination
    Given I am on the travel insurance page
    When I enter "Germany" as the destination
    Then the selected destination should be "Germany"

  Scenario: Validate selecting travel dates
    Given I am on the travel insurance page
    When I enter "Germany" as the destination
    And I select "16" as the start date and "27" as the end date
    Then the selected start date should contain "16"

  Scenario: Validate selecting 2 travellers
    Given I am on the travel insurance page
    When I enter "Germany" as the destination
    And I select "16" as the start date and "27" as the end date
    And I select "21" as age of first traveller and "22" as age of second traveller
    And I submit the traveller details
    Then I should see message "2 Traveller(s)"

  Scenario: Validate viewing plans
    Given I am on the travel insurance page
    When I enter "Germany" as the destination
    And I select "16" as the start date and "27" as the end date
    And I select "21" as age of first traveller and "22" as age of second traveller
    And I submit the traveller details
    And I click on view plans
    Then I should be on the plans page

  Scenario: Validate selecting student plans
    Given I am on the travel insurance page
    When I enter "Germany" as the destination
    And I select "16" as the start date and "27" as the end date
    And I select "21" as age of first traveller and "22" as age of second traveller
    And I submit the traveller details
    And I click on view plans
    And I filter by student plans with duration "30 Days"
    Then student plans should be displayed

  Scenario: Validate sorting plans from low to high
    Given I am on the travel insurance page
    When I enter "Germany" as the destination
    And I select "16" as the start date and "27" as the end date
    And I select "21" as age of first traveller and "22" as age of second traveller
    And I submit the traveller details
    And I click on view plans
    And I filter by student plans with duration "30 Days"
    And I sort plans from low to high
    Then plans should be sorted in ascending order

  Scenario: Validate getting top 3 plans
    Given I am on the travel insurance page
    When I enter "Germany" as the destination
    And I select "16" as the start date and "27" as the end date
    And I select "21" as age of first traveller and "22" as age of second traveller
    And I submit the traveller details
    And I click on view plans
    And I filter by student plans with duration "30 Days"
    And I sort plans from low to high
    And I fetch top 3 plans
    Then I should get company names and insurance amounts

  Scenario: Validate no traveller selected shows error
    Given I am on the travel insurance page
    When I enter "Germany" as the destination
    And I select "16" as the start date and "27" as the end date
    And I click cut button
    And I click on view plans
    Then I should see error message "Please add traveller(s)"

  Scenario: Validate invalid country shows no result
    Given I am on the travel insurance page
    When I enter "ZZZ" as the destination
    Then I should see no result found error

  Scenario: Validate not selecting date shows error
    Given I am on the travel insurance page
    When I enter "Germany" as the destination
    And I select "21" as age of first traveller and "22" as age of second traveller
    And I submit the traveller details
    And I click cut button
    And I click on view plans
    Then I should see error message "Please select trip dates"