Feature: User Account
  Scenario: User signs up for a new account
    Given the user is not logged in 
    When the user enters username  "mahmoud" password "123" and role "BeneficiaryUser"
    Then the user account should be created successfully
    
    
    
  Scenario: User signs in with valid details 
    Given the user is not logged in 
    When the user enters username "mahmoud" and password "123"
    Then user enters to the dash
    
  Scenario: User updates his password 
    Given the user is logged in 
    When I navigate to manage personal account 
    And select update password option for username "mahmoud" and enter the new password "1234"
    Then success message will apear


   Scenario: Post and share personal dessert creations
    Given the user is logged in
    When I choose to post a new dessert creation with details "details"
    Then the dessert creation should be shared successfully
    And user returns to dash
  
  
  
  
  
