Feature: Communication and Notification
  As a store owner or raw material supplier
  I want to communicate with users and other suppliers
  So that I can handle inquiries and collaborate effectively

  Scenario: Use the messaging system to communicate with users
    Given I am logged in as a store owner 
    When I navigate to the Communicate with users page 
    And I select Send a message to a user option and I enter the id of the user "7" and the message "hello man"
    Then i submit
    And Message sent successfully and store Owner return to the main dashboard 

  Scenario: Use the messaging system to communicate with other suppliers
    Given I am logged in as a store owner 
    When I navigate to the Communicate with users page 
    And I select send message to a user option 
    And a list of other Owners ans suplieres will apear
    And i entre the owner or supplier ID "12" and the message "hello"
    Then i submit
    And message sent success and user return to the messaging dashboard 
    
    
    Scenario: user sending message to owner
    Given I am logged in as a user
    When I navigate to the messaging dashboard 
    And I select a store owner "12" and I write a message "hello owner"
    Then i submit
    And message sent success and user return to the messaging dashboard 

