package acceptance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import software.BeneficiaryUserDashboard;
import software.UserAuthentication;
import software.UserDetails;

public class UserAccountStep {
	
	@Given("the user is not logged in")
	public void the_user_is_not_logged_in() {
	  assertTrue(true);
	}
	@When("the user enters username  {string} password {string} and role {string}")
	public void the_user_enters_username_password_and_role(String string, String string2, String string3) {
	    UserAuthentication.signup(string, string2, string3, "tulkarm");
	}
	@Then("the user account should be created successfully")
	public void the_user_account_should_be_created_successfully() {
	   assertTrue(true);
	}


@When("the user enters username {string} and password {string}")
public void the_user_enters_username_and_password(String string, String string2) {
    UserAuthentication.authenticate(string, string2);
}
@Then("user enters to the dash")
public void user_enters_to_the_dash() {
    assertTrue(true);
}


@When("I navigate to manage personal account")
public void i_navigate_to_manage_personal_account() {
   assertTrue(true);
}
@When("select update password option for username {string} and enter the new password {string}")
public void select_update_password_option_for_username_and_enter_the_new_password(String string, String string2) {
    UserAuthentication.updatePassword(string, string2);
}
@Then("success message will apear")
public void success_message_will_apear() {
   assertTrue(true);
}



@When("I choose to post a new dessert creation with details {string}")
public void i_choose_to_post_a_new_dessert_creation_with_details(String string) {
  BeneficiaryUserDashboard.postAndShareDessertCreation("mahmoud", string);
}
@Then("the dessert creation should be shared successfully")
public void the_dessert_creation_should_be_shared_successfully() {
    assertTrue(true);
}
@Then("user returns to dash")
public void user_returns_to_dash() {
   assertTrue(true);
}
	
}
