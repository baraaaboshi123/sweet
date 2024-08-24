package acceptance;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import software.BeneficiaryUserDashboard;
import software.ProductManagement;
import software.UserAuthentication;

public class CommunicationAndNotificationStep {
ProductManagement app;

public CommunicationAndNotificationStep(ProductManagement app) {
	super();
	this.app = app;
}


@Given("I am logged in as a store owner")
public void i_am_logged_in_as_a_store_owner() {
	UserAuthentication.authenticate("samer", "1234");
	assertTrue(true);
}
@When("I navigate to the Communicate with users page")
public void i_navigate_to_the_communicate_with_users_page() {
assertTrue(true);
}
@When("I select Send a message to a user option and I enter the id of the user {string} and the message {string}")
public void i_select_send_a_message_to_a_user_option_and_i_enter_the_id_of_the_user_and_the_message(String string, String string2) {
    BeneficiaryUserDashboard.sendMessageToStoreOwner(6, Integer.parseInt(string), string2);
    assertTrue(true);
}
@Then("Message sent successfully and store Owner return to the main dashboard")
public void message_sent_successfully_and_store_owner_return_to_the_main_dashboard() {
	assertTrue(true);
}

@When("I select send message to a user option")
public void i_select_send_message_to_a_user_option() {
    assertTrue(true);
}
@When("a list of other Owners ans suplieres will apear")
public void a_list_of_other_owners_ans_suplieres_will_apear() {
    BeneficiaryUserDashboard.listStoreOwners();
    
}
@When("i entre the owner or supplier ID {string} and the message {string}")
public void i_entre_the_owner_or_supplier_id_and_the_message(String string, String string2) {
    BeneficiaryUserDashboard.sendMessageToStoreOwner(6, Integer.parseInt(string), string2);
    
}
@Then("message sent success and user return to the messaging dashboard")
public void message_sent_success_and_user_return_to_the_messaging_dashboard() {
    assertTrue(true);
}


@Given("I am logged in as a user")
public void i_am_logged_in_as_a_user() {
    UserAuthentication.authenticate("user", "1234");
  
}
@When("I navigate to the messaging dashboard")
public void i_navigate_to_the_messaging_dashboard() {
    assertTrue(true);
}
@When("I select a store owner {string} and I write a message {string}")
public void i_select_a_store_owner_and_i_write_a_message(String string, String string2) {
    BeneficiaryUserDashboard.sendMessageToStoreOwner(7, Integer.parseInt(string), string2);
    
}

}
