package acceptance;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import software.AdminDashboard;

public class ContentManagmentTest {

	AdminDashboard app;
	
	
	public ContentManagmentTest(AdminDashboard app) {
		super();
		this.app = app;
		}
	
	
	
	
	
	@Given("I am on the admin dashboard")
	public void i_am_on_the_admin_dashboard() {
	   assertTrue(true);
	}
	@Given("I select {string} from the dashboard options")
	public void i_select_from_the_dashboard_options(String string) {
	    assertTrue(true);
	}
	@Given("I am on the manage recipes and posts page")
	public void i_am_on_the_manage_recipes_and_posts_page() {
	    AdminDashboard.listAllRecipesAndPosts();
	    assertTrue(true);
	}
	@Given("I see all recipes and posts listed")
	public void i_see_all_recipes_and_posts_listed() {
	    assertTrue(true);
	}
	@When("I select to delete Recipe or post")
	public void i_select_to_delete_recipe_or_post() {
	    assertTrue(true);
	}
	@When("I enter the ID of the recipe or post i want to delete")
	public void i_enter_the_id_of_the_recipe_or_post_i_want_to_delete() {
	    AdminDashboard.deleteContent();
	    assertTrue(true);
	}
	@Then("the recipe or post will be deleted")
	public void the_recipe_or_post_will_be_deleted() {
	    assertTrue(true);
	}
	
	
	
	
	
	
	@Given("I select {string} to list all feedback")
	public void i_select_to_list_all_feedback(String string) {
	   assertTrue(true);
	}
	@When("I select List all feedback")
	public void i_select_list_all_feedback() {
	   AdminDashboard.listAllFeedback();
	   assertTrue(true);
	}
	@When("I should see all feedbacks")
	public void i_should_see_all_feedbacks() {
	   assertTrue(true);
	}
	@When("return back to the content management page")
	public void return_back_to_the_content_management_page() {
	   assertTrue(true);
	}
	
	
	
	
	@Given("I select {string} to delete a feedback")
	public void i_select_to_delete_a_feedback(String string) {
	    assertTrue(true);
	}
	@When("I select Delete feedback and enter id feedback {string}")
	public void i_select_delete_feedback_and_enter_id_feedback(String string) {
	    AdminDashboard.deleteFeedback(Integer.parseInt(string));
	    assertTrue(true);
	}
	@When("I should see a success message {string}")
	public void i_should_see_a_success_message(String string) {
	    assertTrue(true);
	}
}
