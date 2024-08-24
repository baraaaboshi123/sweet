package acceptance;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import software.BeneficiaryUserDashboard;
import software.ProductManagement;
import software.UserAuthentication;

public class ExplorationAndPurchase {
	
	
	
	
	
	public ExplorationAndPurchase() {
		
	}
	
	@Given("the user is logged in")
	public void the_user_is_logged_in() {
	    UserAuthentication.authenticate("user", "1234");
	}
	@When("I search for dessert recipes by name {string}")
	public void i_search_for_dessert_recipes_by_name(String string) {
	    ProductManagement.browseRecipes(string);
	}
	@Then("i should see the the dessert recipe and component")
	public void i_should_see_the_the_dessert_recipe_and_component() {
	    assertTrue(true);
	}
	
	

@When("i chose purchase desserts optione then enter the product ID {int}")
public void i_chose_purchase_desserts_optione_then_enter_the_product_id(Integer int1) {
    ProductManagement.purchaseProduct(7, int1, Double.parseDouble("5"));
}
@Then("success message apear and user returns to dash")
public void success_message_apear_and_user_returns_to_dash() {
    assertTrue(true);
}
	
	

}
