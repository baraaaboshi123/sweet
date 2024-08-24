Feature: User Management
  As an admin
  I want to manage user accounts including users, store owners, and raw material suppliers
  So that I can control access to the system


  Scenario: Add a new Store Owner user account
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "1" from the dashboard options
    And I am on the Create Store Owner Account page
    When I choose to add a new user and I enter the username "ahmad" , password "123" , and city "Nablus"
    And I submit the new user details
    And I should see a success message "User account created successfully."
    
    
    Scenario: Add a new Raw Material Supplier Account
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "2" from the dashboard options
    And I am on the Create Raw Material Supplier Account page
    When I choose to add a new user and enter the username "ahmad1" , password "123" , and city "Nablus"
    And I submit the new user details
    And I should see a success message "User account created successfully."
    
    

  Scenario: Change user passwords
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "4" from the dashboard options
    And I am on the Change Accounts Passwords page
    And I should see all users in the list
    When I choose to update the user with id "8" and I enter the new password "111" 
    And I submit the updated user password
    And I should see a success message "Password updated successfully for user ID: 8."
