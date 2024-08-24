Feature: Communication and Feedback

  As a beneficiary user,
  I want to directly communicate with store owners and suppliers for inquiries or assistance,
  and provide feedback on purchased products and shared recipes,
  So that I can ensure a satisfying experience and help improve the offerings on the platform.

  Scenario: Direct Communication with Store Owners and Suppliers
    Given I am logged in as user
    And I am on the user dashboard
    When I navigate to Communicate with Store Owners and Suppliers page
    And I choose Send a message to a Store Owner, a list of owners and suppliers will apear
    And I enter the owner or supplier ID "6" and a message "hello"
    Then a success message apear and return back to the user dashboard



  Scenario: Provide Feedback on Purchased Products
    Given I am logged in as user
    And I have received a purchased dessert
    When I navigate to the provide Feedback page
    And I select the product ID "4" to provide feedback on and enter feedback rate out of 5 "5"
    Then I should see a success message
    And return back to the user dashboard

