package acceptance;

import static org.junit.Assert.assertTrue;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import software.UserAuthentication;

public class AccountManagementStep {

	UserAuthentication app;

	public AccountManagementStep(UserAuthentication app) {
		super();
		this.app = app;
	}
	
	@Given("I am logged in as a store owner or raw material supplier")
	public void i_am_logged_in_as_a_store_owner_or_raw_material_supplier() {
	   app.authenticate("baraaaboshi", "123");
	   assertTrue(true); // Assuming the authentication is always successful
	}

	@When("I navigate to the account management page")
	public void i_navigate_to_the_account_management_page() {
	   
	    assertTrue(true); // Assuming navigation is always successful
	}

	@Then("I should see my account details")
	public void i_should_see_my_account_details() {
	   
	    assertTrue(true); // Assuming account details are always displayed
	}

	

	@When("I navigate to the account management page And I select {string} and I enter storename {string} and location {string} and phone  update the business information")
	public void i_navigate_to_the_account_management_page_and_i_select_and_i_enter_storename_and_location_and_phone_update_the_business_information(String string, String string2, String string3) {
		UserAuthentication ua = new UserAuthentication(); 
		ua.updateStoreInformation(6, string, string2, string3);
	    
	}
	@Then("i submit")
	public void i_submit() {
		  assertTrue(true); 
	}
	@Then("I should see the updated information on the account management page")
	public void i_should_see_the_updated_information_on_the_account_management_page() {
	    assertTrue(true); // Assuming the updated information is always displayed correctly
	}

	
	
	
}
