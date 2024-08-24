Feature: Content Management
  As an admin
  I want to manage the content shared on the system
  So that I can maintain quality and relevance

 
  Scenario: View all recipes and posts
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "8" from the dashboard options
    And I am on the manage recipes and posts page 
    And I see all recipes and posts listed
    When I select to delete Recipe or post
    And I enter the ID of the recipe or post i want to delete
    Then the recipe or post will be deleted



  Scenario: view user feedback
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "9" from the dashboard options
    And I select "1" to list all feedback
    When I select List all feedback
    And I should see all feedbacks 
    And return back to the content management page
    
    
     Scenario: delete user feedback
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "9" from the dashboard options
    And I select "2" to delete a feedback
    When I select Delete feedback and enter id feedback "7"
    And I should see a success message "feedback deleted successfully."
    And I should see all feedbacks 
    And return back to the content management page

