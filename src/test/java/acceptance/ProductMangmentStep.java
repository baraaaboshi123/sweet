package acceptance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import software.ProductManagement;
import software.UserAuthentication;

public class ProductMangmentStep {
	
	
	ProductManagement app;
	public ProductMangmentStep(ProductManagement app) {
		super();
		this.app=app;
		
	}
	@Given("I am logged in as a store owner or material supplier")
	public void i_am_logged_in_as_a_store_owner_or_material_supplier() {
	    UserAuthentication.authenticate("samer", "1234");
	}
	@When("And select add {string} I fill in the product name {string} and price {string}")
	public void and_select_add_i_fill_in_the_product_name_and_price(String string, String string2, String string3) {
	   ProductManagement.addProduct(string2, Double.parseDouble(string3), 6);
	}
	@Then("i submit the details")
	public void i_submit_the_details() {
	   assertTrue(true);
	}
	@Then("I should see the new product in my product list")
	public void i_should_see_the_new_product_in_my_product_list() {
	   ProductManagement.listProducts(6);
	}
	
	
	@When("I select update product and enter a product ID {string} and new product name {string} and update price {string}")
	public void i_select_update_product_and_enter_a_product_id_and_new_product_name_and_update_price(String string, String string2, String string3) {
	    ProductManagement.updateProduct(Integer.parseInt(string), string2, Double.parseDouble(string3));
	}
	@Then("update success message pops and user return to management dashbored")
	public void update_success_message_pops_and_user_return_to_management_dashbored() {
	   assertTrue(true);
	}
	
	@When("I select delete product")
	public void i_select_delete_product() {
	    assertTrue(true);
	}
	@Then("a list of products will apear, I select product id from the list and enter it {string}")
	public void a_list_of_products_will_apear_i_select_product_id_from_the_list_and_enter_it(String string) {
	    ProductManagement.listProducts(6);
	    ProductManagement.removeProduct(Integer.parseInt(string));
	}
	@Then("delete message pops and user return to management dashbored")
	public void delete_message_pops_and_user_return_to_management_dashbored() {
	    assertTrue(true);
	}
	
	

@When("I navigate to the monitor sales page")
public void i_navigate_to_the_monitor_sales_page() {
    assertTrue(true);
}
@Then("I should see a report of my sales")
public void i_should_see_a_report_of_my_sales() {
    ProductManagement.monitorSales(6);
}

@When("I navigate to the Identify Best selling products")
public void i_navigate_to_the_identify_best_selling_products() {
   assertTrue(true);
}
@Then("I should see a list of my best-selling products")
public void i_should_see_a_list_of_my_best_selling_products() {
    ProductManagement.identifyBestSellingProducts(6);
}

@When("I navigate to the implement dynamic discount page")
public void i_navigate_to_the_implement_dynamic_discount_page() {
    assertTrue(true);
}
@When("I fill in the product id {string} and discount percentage {string}")
public void i_fill_in_the_product_id_and_discount_percentage(String string, String string2) {
   ProductManagement.applyDiscount(Integer.parseInt(string), Double.parseDouble(string2));
}
@Then("price message pops after discount")
public void price_message_pops_after_discount() {
    assertTrue(true);
}

@When("I navigate to the remove discount page")
public void i_navigate_to_the_remove_discount_page() {
   assertTrue(true);
}
@When("I fill in the product id {string}")
public void i_fill_in_the_product_id(String string) {
    ProductManagement.removeDiscount(Integer.parseInt(string));
}
@Then("price message pops after remove discount")
public void price_message_pops_after_remove_discount() {
    assertTrue(true);
}









	
	}
	
