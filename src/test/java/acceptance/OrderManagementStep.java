package acceptance;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import software.ProductManagement;

public class OrderManagementStep {
ProductManagement app;
int orderId;
String status;

public OrderManagementStep(ProductManagement app) {
	super();
	this.app = app;
}



@When("I navigate to the order management page")
public void i_navigate_to_the_order_management_page() {
	assertTrue(true);
}
@Then("I should see a list of orders")
public void i_should_see_a_list_of_orders() {
    ProductManagement.listStoreOrders(6);
}

@Given("I am on the order management page")
public void i_am_on_the_order_management_page() {
   assertTrue(true);
}
@When("I select an order by ID {string}")
public void i_select_an_order_by_id(String string) {
	orderId = Integer.parseInt(string);
    assertTrue(true);
}
@Then("then i entered the status update for that order {string}")
public void then_i_entered_the_status_update_for_that_order(String string) {
	ProductManagement.updateOrderStatus(orderId, string);
   
}
@Then("I should see the updated status in the order list and return to management page")
public void i_should_see_the_updated_status_in_the_order_list_and_return_to_management_page() {
   ProductManagement.listStoreOrders(6);
}

@Then("I should see the current status of all orders")
public void i_should_see_the_current_status_of_all_orders() {
	ProductManagement.listStoreOrders(6);
}
 }
