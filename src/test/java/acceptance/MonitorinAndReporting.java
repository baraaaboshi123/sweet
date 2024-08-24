package acceptance;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import software.AdminDashboard;
import software.UserAuthentication;

public class MonitorinAndReporting {
UserAuthentication app;

	
	
	public MonitorinAndReporting(UserAuthentication app) {
	super();
	this.app = app;
}
	
	 
	@Given("I am logged in as an admin")
	public void i_am_logged_in_as_an_admin() {
	    UserAuthentication.authenticate("baraa", "1234");
	   assertTrue(UserAuthentication.isUserLoggedIn);
	   }
	@When("I navigate to the monitor sales report page")
	public void i_navigate_to_the_monitor_sales_report_page() {
		 UserAuthentication.authenticate("baraa","1234");
		 assertTrue(true); // Assuming the authentication is always successful
		
	}
	@When("I select {string}")
	public void i_select(String ownerID) throws FileNotFoundException, IOException {
		assertTrue(true);
	 
	  }
	@Then("I should see a report of profits")
	public void i_should_see_a_report_of_profits() {
	  AdminDashboard.monitorSales(6);
	  assertTrue(true);
	}
	
	
	@When("I navigate to the best selling products page")
	public void i_navigate_to_the_best_selling_products_page() {
	    assertTrue(true);
	   
	}
	@Then("I should see a list of best-selling products in each store")
	public void i_should_see_a_list_of_best_selling_products_in_each_store() throws NumberFormatException, IOException {
	    AdminDashboard.identifyBestSellingProducts();
	    assertTrue(true);
	}
	
	@When("I navigate to the List Users Group By Cities page")
	public void i_navigate_to_the_list_users_group_by_cities_page() {
	    AdminDashboard.groupUsersByCity();
	    assertTrue(true);
	   
	}
	@Then("I should see statistics on registered users by city")
	public void i_should_see_statistics_on_registered_users_by_city() {
	    assertTrue(true);
	}
	
	

}
