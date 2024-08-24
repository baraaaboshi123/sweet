Feature: Product Management
  As a store owner or raw material supplier
  I want to manage my products
  So that I can keep my inventory up to date and track sales performance

  Scenario: Add a new product
    Given I am logged in as a store owner or material supplier
    When And select add "Add Product" I fill in the product name "molto" and price "20" 
    Then i submit the details
    And I should see the new product in my product list

  Scenario: Update an existing product
    Given I am logged in as a store owner or material supplier
    When I select update product and enter a product ID "5" and new product name "molto" and update price "35"
    Then i submit
    And update success message pops and user return to management dashbored

  Scenario: Remove a product
    Given I am logged in as a store owner or material supplier
    When I select delete product
    Then a list of products will apear, I select product id from the list and enter it "5"
    And delete message pops and user return to management dashbored

  Scenario: Monitor sales and profits
    Given I am logged in as a store owner or material supplier
    When I navigate to the monitor sales page
    Then I should see a report of my sales

  Scenario: Identify best-selling products
    Given I am logged in as a store owner or material supplier
    When I navigate to the Identify Best selling products 
    Then I should see a list of my best-selling products

  Scenario: Implement dynamic discount features
    Given I am logged in as a store owner or material supplier
    When I navigate to the implement dynamic discount page 
    And I fill in the product id "4" and discount percentage "20"
    Then i submit
    And price message pops after discount 
    
     Scenario: Remove dynamic discount for a product
    Given I am logged in as a store owner or material supplier
    When I navigate to the remove discount page 
    And I fill in the product id "4" 
    Then i submit
    And price message pops after remove discount 
