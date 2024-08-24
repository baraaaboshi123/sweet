Feature: Order Management
  As a store owner or raw material supplier
  I want to process and track orders
  So that I can manage my sales efficiently

  Scenario: View orders
    Given I am logged in as a store owner 
    When I navigate to the order management page
    Then I should see a list of orders

  Scenario: Process an order
    Given I am logged in as a store owner
    And I am on the order management page
    When I select an order by ID "1"
    Then then i entered the status update for that order "Porcessing"
    And I should see the updated status in the order list and return to management page

  Scenario: Track order status
    Given I am logged in as a store owner
    When I navigate to the order management page
    Then I should see the current status of all orders 
