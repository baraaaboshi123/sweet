package software;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class AdminDashboard {
	  private static final String DASHED_LINE = "------------------------------------";
	public static void createUserAccount(String userName, String city, String password, String role) {
	    String query = "INSERT INTO users (user_name, city, password, role) VALUES (?, ?, ?, ?)";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        statement.setString(1, userName);
	        statement.setString(2, city);
	        statement.setString(3, password);
	        statement.setString(4, role);

	        int rowsInserted = statement.executeUpdate();

	        if (rowsInserted > 0) {
	            System.out.println("User account created successfully.");
	        } else {
	            System.out.println("Failed to create user account.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void listAllUserAccounts() {
	    String query = "SELECT idusers, user_name, city, role FROM users";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        ResultSet resultSet = statement.executeQuery();

	        System.out.println("List of All User Accounts:");
	        System.out.println("ID | Username | City | Role");
	        System.out.println(DASHED_LINE);

	        while (resultSet.next()) {
	            int userId = resultSet.getInt("idusers");
	            String userName = resultSet.getString("user_name");
	            String city = resultSet.getString("city");
	            String role = resultSet.getString("role");

	            System.out.println(userId + " | " + userName + " | " + city + " | " + role);
	        }
	        System.out.println(DASHED_LINE);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void changeUserPassword(int userId, String newPassword) {
	    String query = "UPDATE users SET password = ? WHERE idusers = ?";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        statement.setString(1, newPassword);
	        statement.setInt(2, userId);

	        int rowsUpdated = statement.executeUpdate();

	        if (rowsUpdated > 0) {
	            System.out.println("Password updated successfully for user ID: " + userId);
	        } else {
	            System.out.println("Failed to update password. User ID not found.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void monitorSales(int storeOwnerId) {
	    String query = "SELECT SUM(orders.total_price) AS total_sales, COUNT(orders.idorders) AS total_orders " +
	                   "FROM orders " +
	                   "JOIN products ON orders.product_id = products.idproducts " +
	                   "WHERE products.owner_id = ? AND orders.status = 'Completed'";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        statement.setInt(1, storeOwnerId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            double totalSales = resultSet.getDouble("total_sales");
	            int totalOrders = resultSet.getInt("total_orders");

	            System.out.println("Financial Report for Store Owner ID: " + storeOwnerId);
	            System.out.println("Total Sales: $" + totalSales);
	            System.out.println("Total Completed Orders: " + totalOrders);
	            System.out.println("------------------------------------");
	        } else {
	            System.out.println("No sales data found for this store owner.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void listAllStoresWithOwners() {
	    String query = "SELECT stores.name, users.user_name AS owner_name, users.idusers AS owner_id " +
	                   "FROM stores " +
	                   "JOIN users ON stores.owner_id = users.idusers";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        ResultSet resultSet = statement.executeQuery();

	        System.out.println("List of All Stores and Their Owners:");
	        System.out.println("Store Name | Owner Name | Owner ID");
	        System.out.println("----------------------------------------");

	        while (resultSet.next()) {
	            String storeName = resultSet.getString("name");
	            String ownerName = resultSet.getString("owner_name");
	            int ownerId = resultSet.getInt("owner_id");

	            System.out.println(storeName + " | " + ownerName + " | " + ownerId);
	        }
	        System.out.println("----------------------------------------");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void identifyBestSellingProducts() {
	    String query = "SELECT stores.name, products.name AS product_name, COUNT(orders.idorders) AS sales_count " +
	                   "FROM orders " +
	                   "JOIN products ON orders.product_id = products.idproducts " +
	                   "JOIN stores ON products.owner_id = stores.owner_id " +
	                   "GROUP BY stores.name, products.name " +
	                   "ORDER BY stores.name, sales_count DESC";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        ResultSet resultSet = statement.executeQuery();

	        String currentStoreName = "";
	        System.out.println("Best-Selling Products in Each Store:");
	        System.out.println("--------------------------------------");

	        while (resultSet.next()) {
	            String storeName = resultSet.getString("name");
	            String productName = resultSet.getString("product_name");
	            int salesCount = resultSet.getInt("sales_count");

	            if (!storeName.equals(currentStoreName)) {
	                currentStoreName = storeName;
	                System.out.println("Store: " + storeName);
	            }

	            System.out.println("Product: " + productName + " | Sales Count: " + salesCount);
	            System.out.println("--------------------------------------");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void groupUsersByCity() {
	    String[] cities = {"Tulkarm", "Nablus", "Jenin", "Ramallah", "Tubas", "Hebron", "Jericho", "Qalqilya"};
	    String query = "SELECT idusers, user_name, LOWER(city) AS city_lower FROM users WHERE LOWER(city) = ?";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        for (String city : cities) {
	            statement.setString(1, city.toLowerCase());
	            ResultSet resultSet = statement.executeQuery();

	            System.out.println("Users from " + city + ":");
	            boolean hasUsers = false;

	            while (resultSet.next()) {
	                hasUsers = true;
	                int userId = resultSet.getInt("idusers");
	                String userName = resultSet.getString("user_name");

	                System.out.println("User ID: " + userId + " | Name: " + userName);
	            }

	            if (!hasUsers) {
	                System.out.println("No users found in " + city);
	            }

	            System.out.println("--------------------------------------");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void listAllRecipesAndPosts() {
	    // List all recipes
	    String recipeQuery = "SELECT id, name, description, ingredients, dietary_info, user_id FROM recipes";
	    String postQuery = "SELECT id, user_name, details FROM dessert_creations";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement recipeStatement = connection.prepareStatement(recipeQuery);
	         PreparedStatement postStatement = connection.prepareStatement(postQuery)) {

	        ResultSet recipeResultSet = recipeStatement.executeQuery();
	        ResultSet postResultSet = postStatement.executeQuery();

	        System.out.println("Recipes:");
	        System.out.println("--------------------------------------");
	        while (recipeResultSet.next()) {
	            int recipeId = recipeResultSet.getInt("id");
	            String name = recipeResultSet.getString("name");
	            String description = recipeResultSet.getString("description");
	            String ingredients = recipeResultSet.getString("ingredients");
	            String dietaryInfo = recipeResultSet.getString("dietary_info");
	            int userId = recipeResultSet.getInt("user_id");

	            System.out.println("Recipe ID: " + recipeId);
	            System.out.println("Name: " + name);
	            System.out.println("Description: " + description);
	            System.out.println("Ingredients: " + ingredients);
	            System.out.println("Dietary Info: " + dietaryInfo);
	            System.out.println("Store Owner ID: " + userId);
	            System.out.println("--------------------------------------");
	        }

	        System.out.println("Posts:");
	        System.out.println("--------------------------------------");
	        while (postResultSet.next()) {
	            int postId = postResultSet.getInt("id");
	            String userName = postResultSet.getString("user_name");
	            String details = postResultSet.getString("details");

	            System.out.println("Post ID: " + postId);
	            System.out.println("User Name: " + userName);
	            System.out.println("Details: " + details);
	            System.out.println("--------------------------------------");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void deleteContent() {
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Do you want to delete a recipe or a post?");
	    System.out.println("1. Recipe");
	    System.out.println("2. Post");
	    System.out.print("Enter your choice: ");
	    int choice = scanner.nextInt();

	    System.out.print("Enter the ID of the item to delete: ");
	    int id = scanner.nextInt();

	    if (choice == 1) {
	        deleteRecipe(id);
	    } else if (choice == 2) {
	        deletePost(id);
	    } else {
	        System.out.println("Invalid choice. Please select a valid option.");
	    }

	   
	}

	private static void deleteRecipe(int recipeId) {
    String deleteFeedbackQuery = "DELETE FROM feedback WHERE recipe_id = ?";
    String deleteRecipeQuery = "DELETE FROM recipes WHERE id = ?";

    // Use try-with-resources to ensure resources are closed automatically
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement deleteFeedbackStatement = connection.prepareStatement(deleteFeedbackQuery);
         PreparedStatement deleteRecipeStatement = connection.prepareStatement(deleteRecipeQuery)) {

        connection.setAutoCommit(false);  // Start transaction

        // Delete feedback associated with the recipe
        deleteFeedbackStatement.setInt(1, recipeId);
        int feedbackRowsAffected = deleteFeedbackStatement.executeUpdate();

        // Delete the recipe
        deleteRecipeStatement.setInt(1, recipeId);
        int recipeRowsAffected = deleteRecipeStatement.executeUpdate();

        if (recipeRowsAffected > 0) {
            connection.commit();  // Commit transaction
            System.out.println("Recipe with ID " + recipeId + " and its associated feedback have been deleted successfully.");
        } else {
            connection.rollback();  // Rollback transaction if no recipe was deleted
            System.out.println("No recipe found with ID " + recipeId);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}



	private static void deletePost(int postId) {
	    String query = "DELETE FROM dessert_creations WHERE id = ?";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        statement.setInt(1, postId);
	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Post with ID " + postId + " has been deleted successfully.");
	        } else {
	            System.out.println("No post found with ID " + postId);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void listAllFeedback() {
	    String query = "SELECT f.idfeedback, u.user_name, p.name AS product_name, f.rating " +
	                   "FROM feedback f " +
	                   "JOIN users u ON f.user_id = u.idusers " +
	                   "JOIN products p ON f.product_id = p.idproducts";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query);
	         ResultSet resultSet = statement.executeQuery()) {

	        System.out.println("All User Feedback:");
	        while (resultSet.next()) {
	            int feedbackId = resultSet.getInt("idfeedback");
	            String userName = resultSet.getString("user_name");
	            String productName = resultSet.getString("product_name");
	            int rating = resultSet.getInt("rating");

	            System.out.println("Feedback ID: " + feedbackId);
	            System.out.println("User: " + userName);
	            System.out.println("Product: " + productName);
	            System.out.println("Rating: " + rating);
	            System.out.println("-------------------------------------------------");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void deleteFeedback(int feedbackId) {
	    String query = "DELETE FROM feedback WHERE idfeedback = ?";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        statement.setInt(1, feedbackId);
	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Feedback deleted successfully!");
	        } else {
	            System.out.println("No feedback found with the specified ID.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
