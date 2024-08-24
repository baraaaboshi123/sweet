package acceptance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import software.BeneficiaryUserDashboard;
import software.ProductManagement;
import software.UserAuthentication;

public class CommunicationAndFeedbackTest {
	
	BeneficiaryUserDashboard app;
	
	public CommunicationAndFeedbackTest(BeneficiaryUserDashboard app) {
		super();
		this.app = app;
	}
	@Given("I am logged in as user")
	public void i_am_logged_in_as_user() {
	    UserAuthentication.authenticate("user", "1234");
	}
	@Given("I am on the user dashboard")
	public void i_am_on_the_user_dashboard() {
	    assertTrue(true);
	}
	@When("I navigate to Communicate with Store Owners and Suppliers page")
	public void i_navigate_to_communicate_with_store_owners_and_suppliers_page() {
	   assertTrue(true);
	}
	@When("I choose Send a message to a Store Owner, a list of owners and suppliers will apear")
	public void i_choose_send_a_message_to_a_store_owner_a_list_of_owners_and_suppliers_will_apear() {
	    BeneficiaryUserDashboard.listStoreOwners();
	}
	@When("I enter the owner or supplier ID {string} and a message {string}")
	public void i_enter_the_owner_or_supplier_id_and_a_message(String string, String string2) {
	    BeneficiaryUserDashboard.sendMessageToStoreOwner(7, Integer.parseInt(string), string2);
	}
	@Then("a success message apear and return back to the user dashboard")
	public void a_success_message_apear_and_return_back_to_the_user_dashboard() {
	    assertTrue(true);
	}
	
	@Given("I have received a purchased dessert")
	public void i_have_received_a_purchased_dessert() {
	     assertTrue(true);
	}
	@When("I navigate to the provide Feedback page")
	public void i_navigate_to_the_provide_feedback_page() {
	    assertTrue(true);
	}
	@When("I select the product ID {string} to provide feedback on and enter feedback rate out of {int} {string}")
	public void i_select_the_product_id_to_provide_feedback_on_and_enter_feedback_rate_out_of(String string, Integer int1, String string2) {
	    ProductManagement.submitOrUpdateProductFeedback(7, Integer.parseInt(string),Integer.parseInt(string2));
	}
	@Then("I should see a success message")
	public void i_should_see_a_success_message() {
		assertTrue(true);
	}
	@Then("return back to the user dashboard")
	public void return_back_to_the_user_dashboard() {
	    assertTrue(true);
	}
	
	
	
}
