package acceptance;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import software.AdminDashboard;

public class UserManagement {

    AdminDashboard app;

    public UserManagement(AdminDashboard app) {
		super();
		this.app = app;
	}

    
    @Given("I am on the Create Store Owner Account page")
    public void i_am_on_the_create_store_owner_account_page() {
        assertTrue(true);
    }
    @When("I choose to add a new user and I enter the username {string} , password {string} , and city {string}")
    public void i_choose_to_add_a_new_user_and_i_enter_the_username_password_and_city(String string, String string2, String string3) {
        AdminDashboard.createUserAccount(string, string3, string2, "StoreOwner");
        assertTrue(true);
    }
    @When("I submit the new user details")
    public void i_submit_the_new_user_details() {
        assertTrue(true);
    }
    
    
    
    @Given("I am on the Create Raw Material Supplier Account page")
    public void i_am_on_the_create_raw_material_supplier_account_page() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }
    @When("I choose to add a new user and enter the username {string} , password {string} , and city {string}")
    public void i_choose_to_add_a_new_user_and_enter_the_username_password_and_city(String string, String string2, String string3) {
    	 AdminDashboard.createUserAccount(string, string3, string2, "RawMaterialSuppliers");
    	 assertTrue(true);
    }
    
    
    @Given("I am on the Change Accounts Passwords page")
    public void i_am_on_the_change_accounts_passwords_page() {
        assertTrue(true);
    }
    @Given("I should see all users in the list")
    public void i_should_see_all_users_in_the_list() {
        AdminDashboard.listAllUserAccounts();
        assertTrue(true);
    }
    @When("I choose to update the user with id {string} and I enter the new password {string}")
    public void i_choose_to_update_the_user_with_id_and_i_enter_the_new_password(String string, String string2) {
   AdminDashboard.changeUserPassword(Integer.parseInt(string), string2);
   assertTrue(true);
    }
    @When("I submit the updated user password")
    public void i_submit_the_updated_user_password() {
        assertTrue(true);
    }


}
