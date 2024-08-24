Feature: Browse and Search for Dessert Recipes

  As a user
  I want to browse, search, and filter dessert recipes
  So that I can find desserts that meet my dietary needs or preferences
  
  
   Scenario: Filter dessert recipes by name or dietary info
    Given the user is logged in    
    And I am on the user dashboard 
    When I search for dessert recipes by name "bread"
    Then i should see the the dessert recipe and component

    Scenario: User make some Purchase
    Given the user is logged in 
    When i chose purchase desserts optione then enter the product ID 4 
    Then success message apear and user returns to dash
    
  
