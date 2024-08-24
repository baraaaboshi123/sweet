Feature: Monitoring and Reporting
  As an admin
  I want to monitor profits and generate financial reports
  So that I can analyze business performance

  Scenario: View profit reports
    Given I am logged in as an admin
    When I navigate to the monitor sales report page
    And I select "Owner ID"
    Then I should see a report of profits


  Scenario: Identify best-selling products in each store
    Given I am logged in as an admin
    When I navigate to the best selling products page
    Then I should see a list of best-selling products in each store

  Scenario: Gather and display statistics on registered users by City
    Given I am logged in as an admin
    When I navigate to the List Users Group By Cities page
    And I select "User Statistics by City"
    Then I should see statistics on registered users by city
  
