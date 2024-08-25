package software;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ProductManagement {
	public static void addProduct(String name, double price, int ownerId) {
    // Establish a connection to the database
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "INSERT INTO Products (name, price, owner_id) VALUES (?, ?, ?)")) {

       
        preparedStatement.setString(1, name); 
        preparedStatement.setDouble(2, price); 
        preparedStatement.setInt(3, ownerId);  

        // Execute the update (insertion)
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Failed to add the product.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("An error occurred while adding the product.");
    }
}

    
	public static void updateProduct(int productId, String name, double price) {
        String query = "UPDATE products SET name = ?, price = ? WHERE idproducts = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            // Set the parameters for the query
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setInt(3, productId);
            
            // Execute the update
            int rowsAffected = statement.executeUpdate();
            
            // Optional: Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Product not found or update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
            System.out.println("Error updating product: " + e.getMessage());
        }
    }
    
	public static void removeProduct(int productId) {
        String query = "DELETE FROM products WHERE idproducts = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            // Set the product ID parameter
            statement.setInt(1, productId);
            
            // Execute the delete operation
            int rowsAffected = statement.executeUpdate();
            
            // Optional: Check if the delete was successful
            if (rowsAffected > 0) {
                System.out.println("Product removed successfully.");
            } else {
                System.out.println("Product not found or delete failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
            System.out.println("Error removing product: " + e.getMessage());
        }
    }
	public static double calculateFinalPrice(int productId) {
    String query = "SELECT price, discount_percentage FROM products WHERE idproducts = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            double price = resultSet.getDouble("price");
            double discountPercentage = resultSet.getDouble("discount_percentage");

            // Directly return the calculated final price
            return price - (price * (discountPercentage / 100));
        } else {
            System.out.println("Product not found.");
            return 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}

	public static void listProducts(int ownerId) {
	    String query = "SELECT p.idproducts, p.name, p.price, COALESCE(AVG(f.rating), 0) AS avg_rating " +
	                   "FROM products p " +
	                   "LEFT JOIN feedback f ON p.idproducts = f.product_id " +
	                   "WHERE p.owner_id = ? " +
	                   "GROUP BY p.idproducts, p.name, p.price";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        statement.setInt(1, ownerId);
	        ResultSet resultSet = statement.executeQuery();

	        if (!resultSet.next()) {
	            System.out.println("No products found for this owner.");
	            return;
	        }

	        System.out.println("Product ID | Product Name | Price | Price After Discount | Average Rating");
	        System.out.println("----------------------------------------------------------------------------");

	        do {
	            int id = resultSet.getInt("idproducts");
	            String name = resultSet.getString("name");
	            double price = resultSet.getDouble("price");
	            double avgRating = resultSet.getDouble("avg_rating");
	            double finalPrice = calculateFinalPrice(id);

	            System.out.printf("%d      | %s          | %.2f | %.2f                | %.2f%n", id, name, price, finalPrice, avgRating);
	        } while (resultSet.next());

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error listing products: " + e.getMessage());
	    }
	}

	 public static void postRecipe(int userId, String name, String description, String ingredients,  String dietaryInfo) {
		    String query = "INSERT INTO recipes (user_id, name, description, ingredients, dietary_info) VALUES (?, ?, ?, ?, ?)";
		    
		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {

		        statement.setInt(1, userId);  // User's user ID
		        statement.setString(2, name);
		        statement.setString(3, description);
		        statement.setString(4, ingredients);
		        statement.setString(5, dietaryInfo);
		        
		        int rowsAffected = statement.executeUpdate();
		        
		        if (rowsAffected > 0) {
		            System.out.println("Recipe posted successfully!");
		        } else {
		            System.out.println("Failed to post recipe.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	 public static void browseRecipes() {
		    String query = "SELECT r.id AS recipe_id, r.name AS recipe_name, r.description, r.ingredients, r.dietary_info, " +
		                   "COALESCE(AVG(f.rating), 0) AS avg_rating " +
		                   "FROM recipes r " +
		                   "LEFT JOIN feedback f ON r.id = f.recipe_id " +
		                   "GROUP BY r.id, r.name, r.description, r.ingredients, r.dietary_info";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query);
		         ResultSet resultSet = statement.executeQuery()) {

		        while (resultSet.next()) {
		            int id = resultSet.getInt("recipe_id");
		            String name = resultSet.getString("recipe_name");
		            String description = resultSet.getString("description");
		            String ingredients = resultSet.getString("ingredients");
		            String dietaryInfo = resultSet.getString("dietary_info");
		            double avgRating = resultSet.getDouble("avg_rating");

		            System.out.println("Recipe ID: " + id);
		            System.out.println("Name: " + name);
		            System.out.println("Description: " + description);
		            System.out.println("Ingredients: " + ingredients);
		            System.out.println("Dietary Information: " + dietaryInfo);
		            System.out.println("Average Rating: " + avgRating);
		            System.out.println("-------------------------------------------------");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	 public static void browseRecipes(String filter) {
		    String query = "SELECT id, name, description, ingredients, dietary_info FROM recipes WHERE name LIKE ? OR dietary_info LIKE ?";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {

		        statement.setString(1, "%" + filter + "%");
		        statement.setString(2, "%" + filter + "%");
		        ResultSet resultSet = statement.executeQuery();

		        while (resultSet.next()) {
		            int id = resultSet.getInt("id");
		            String name = resultSet.getString("name");
		            String description = resultSet.getString("description");
		            String ingredients = resultSet.getString("ingredients");
		            
		            String dietaryInfo = resultSet.getString("dietary_info");

		            System.out.println("Recipe ID: " + id);
		            System.out.println("Name: " + name);
		            System.out.println("Description: " + description);
		            System.out.println("Ingredients: " + ingredients);
		         
		            System.out.println("Dietary Information: " + dietaryInfo);
		            System.out.println("-------------------------------------------------");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	 public static void listAllProducts() {
		    String query = "SELECT p.idproducts AS product_id, p.name AS product_name, p.price, u.idusers AS owner_id, u.user_name AS owner_name, " +
		                   "COALESCE(AVG(f.rating), 0) AS avg_rating " +
		                   "FROM products p " +
		                   "JOIN users u ON p.owner_id = u.idusers " +
		                   "LEFT JOIN feedback f ON p.idproducts = f.product_id " +
		                   "GROUP BY p.idproducts, p.name, p.price, u.idusers, u.user_name";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query);
		         ResultSet resultSet = statement.executeQuery()) {

		        while (resultSet.next()) {
		            int productId = resultSet.getInt("product_id");
		            String productName = resultSet.getString("product_name");
		            double price = resultSet.getDouble("price");
		            int ownerId = resultSet.getInt("owner_id");
		            String ownerName = resultSet.getString("owner_name");
		            double avgRating = resultSet.getDouble("avg_rating");
		            double finalPrice = calculateFinalPrice(productId);

		            System.out.println("Product ID: " + productId);
		            System.out.println("Product Name: " + productName);
		            System.out.println("Price: $" + price);
		            System.out.println("Price After Discount: $" + finalPrice);
		            System.out.println("Average Rating: " + avgRating);
		            System.out.println("Store Owner ID: " + ownerId);
		            System.out.println("Store Owner Name: " + ownerName);
		            System.out.println("-------------------------------------------------");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	 public static void purchaseProduct(int userId, int productId, double totalPrice) {
		    String query = "INSERT INTO orders (user_id, product_id,total_price) VALUES (?, ?, ?)";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {

		        statement.setInt(1, userId);
		        statement.setInt(2, productId);
		        statement.setDouble(3, totalPrice);

		        int rowsAffected = statement.executeUpdate();

		        if (rowsAffected > 0) {
		            System.out.println("Purchase successful!");
		        } else {
		            System.out.println("Failed to complete purchase.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	 public static void listPurchasedProducts(int userId) {
		    String query = "SELECT products.idproducts AS product_id, products.name AS product_name, orders.idorders AS order_id " +
		                   "FROM orders " +
		                   "JOIN products ON orders.product_id = products.idproducts " +
		                   "WHERE orders.user_id = ?";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {

		        statement.setInt(1, userId);
		        ResultSet resultSet = statement.executeQuery();

		        System.out.println("Your Purchased Products:");
		        while (resultSet.next()) {
		            int productId = resultSet.getInt("product_id");
		            String productName = resultSet.getString("product_name");
		            int orderId = resultSet.getInt("order_id");

		            System.out.println("Order ID: " + orderId);
		            System.out.println("Product ID: " + productId);
		            System.out.println("Product Name: " + productName);
		            System.out.println("-------------------------------------------------");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	 public static void submitOrUpdateProductFeedback(int userId, int productId, int rating) {
		    String checkQuery = "SELECT COUNT(*) FROM feedback WHERE user_id = ? AND product_id = ?";
		    String updateQuery = "UPDATE feedback SET rating = ? WHERE user_id = ? AND product_id = ?";
		    String insertQuery = "INSERT INTO feedback (user_id, product_id, rating) VALUES (?, ?, ?)";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
		         PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
		         PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

		        // Check if feedback already exists
		        checkStatement.setInt(1, userId);
		        checkStatement.setInt(2, productId);
		        ResultSet resultSet = checkStatement.executeQuery();

		        resultSet.next();
		        int count = resultSet.getInt(1);

		        if (count > 0) {
		            // Update existing feedback
		            updateStatement.setInt(1, rating);
		            updateStatement.setInt(2, userId);
		            updateStatement.setInt(3, productId);
		            int rowsAffected = updateStatement.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("Feedback updated successfully!");
		            } else {
		                System.out.println("Failed to update feedback.");
		            }
		        } else {
		            // Insert new feedback
		            insertStatement.setInt(1, userId);
		            insertStatement.setInt(2, productId);
		            insertStatement.setInt(3, rating);
		            int rowsAffected = insertStatement.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("Feedback submitted successfully!");
		            } else {
		                System.out.println("Failed to submit feedback.");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	 public static void submitRecipeFeedback(int userId, int recipeId, int rating) {
		    String query = "INSERT INTO feedback (user_id, recipe_id, rating) VALUES (?, ?, ?)";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {

		        statement.setInt(1, userId);
		        statement.setInt(2, recipeId);
		        statement.setInt(3, rating);

		        int rowsAffected = statement.executeUpdate();

		        if (rowsAffected > 0) {
		            System.out.println("Feedback submitted successfully!");
		        } else {
		            System.out.println("Failed to submit feedback.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	 public static void monitorSales(int storeOwnerId) {
		    String query = "SELECT p.idproducts AS product_id, p.name AS product_name, " +
		                   "COUNT(o.product_id) AS total_units_sold, " +
		                   "SUM(p.price) AS total_sales " +
		                   "FROM orders o " +
		                   "JOIN products p ON o.product_id = p.idproducts " +
		                   "WHERE p.owner_id = ? " +
		                   "GROUP BY p.idproducts, p.name";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {

		        statement.setInt(1, storeOwnerId);
		        ResultSet resultSet = statement.executeQuery();

		        System.out.println("Sales Report:");
		        while (resultSet.next()) {
		            int productId = resultSet.getInt("product_id");
		            String productName = resultSet.getString("product_name");
		            int totalUnitsSold = resultSet.getInt("total_units_sold");
		            double totalSales = resultSet.getDouble("total_sales");

		            System.out.println("Product ID: " + productId);
		            System.out.println("Product Name: " + productName);
		            System.out.println("Total Units Sold: " + totalUnitsSold);
		            System.out.println("Total Sales: $" + totalSales);
		            System.out.println("-------------------------------------------------");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	 public static void identifyBestSellingProducts(int storeOwnerId) {
		    String query = "SELECT p.idproducts AS product_id, p.name AS product_name, " +
		                   "COUNT(o.product_id) AS total_units_sold " +
		                   "FROM orders o " +
		                   "JOIN products p ON o.product_id = p.idproducts " +
		                   "WHERE p.owner_id = ? " +
		                   "GROUP BY p.idproducts, p.name " +
		                   "ORDER BY total_units_sold DESC " +
		                   "LIMIT 5"; // Adjust the LIMIT value to display more or fewer products

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {

		        statement.setInt(1, storeOwnerId);
		        ResultSet resultSet = statement.executeQuery();

		        System.out.println("Best-Selling Products:");
		        while (resultSet.next()) {
		            int productId = resultSet.getInt("product_id");
		            String productName = resultSet.getString("product_name");
		            int totalUnitsSold = resultSet.getInt("total_units_sold");

		            System.out.println("Product ID: " + productId);
		            System.out.println("Product Name: " + productName);
		            System.out.println("Total Units Sold: " + totalUnitsSold);
		            System.out.println("-------------------------------------------------");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	 public static void applyDiscount(int productId, double discountPercentage) {
		    String query = "UPDATE products SET discount_percentage = ? WHERE idproducts = ?";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {

		        statement.setDouble(1, discountPercentage);
		        statement.setInt(2, productId);

		        int rowsUpdated = statement.executeUpdate();

		        if (rowsUpdated > 0) {
		            System.out.println("Discount applied successfully.");
		        } else {
		            System.out.println("Failed to apply discount. Product not found.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	 public static void removeDiscount(int productId) {
		    String query = "UPDATE products SET discount_percentage = ? WHERE idproducts = ?";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {

		        statement.setDouble(1, 0);
		        statement.setInt(2, productId);

		        int rowsUpdated = statement.executeUpdate();

		        if (rowsUpdated > 0) {
		            System.out.println("Discount removed successfully.");
		        } else {
		            System.out.println("Failed to remove discount. Product not found.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	 public static void listStoreOrders(int ownerId) {
		    String query = "SELECT orders.idorders AS order_id, products.name AS product_name, users.user_name AS customer_name, orders.status AS order_status " +
		                   "FROM orders " +
		                   "JOIN products ON orders.product_id = products.idproducts " +
		                   "JOIN users ON orders.user_id = users.idusers " +
		                   "WHERE products.owner_id = ?";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {

		        statement.setInt(1, ownerId);
		        ResultSet resultSet = statement.executeQuery();

		        System.out.println("Orders Received by Your Store:");
		        while (resultSet.next()) {
		            int orderId = resultSet.getInt("order_id");
		            String productName = resultSet.getString("product_name");
		            String customerName = resultSet.getString("customer_name");
		            String orderStatus = resultSet.getString("order_status");

		            System.out.println("Order ID: " + orderId);
		            System.out.println("Product Name: " + productName);
		            System.out.println("Customer: " + customerName);
		            System.out.println("Status: " + orderStatus);
		            System.out.println("-------------------------------------------------");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	 public static void updateOrderStatus(int orderId, String newStatus) {
		    String query = "UPDATE orders SET status = ? WHERE idorders = ?";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {

		        statement.setString(1, newStatus);
		        statement.setInt(2, orderId);

		        int rowsUpdated = statement.executeUpdate();

		        if (rowsUpdated > 0) {
		            System.out.println("Order status updated successfully.");
		        } else {
		            System.out.println("Order not found or status unchanged.");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	 public static void printStoreInformation(int ownerId) {
		    String query = "SELECT name, address, phone FROM stores WHERE owner_id = ?";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {

		        statement.setInt(1, ownerId);
		        ResultSet resultSet = statement.executeQuery();

		        if (resultSet.next()) {
		            String storeName = resultSet.getString("name");
		            String location = resultSet.getString("address");
		            String phone = resultSet.getString("phone");

		            System.out.println("Store Information:");
		            System.out.println("Store Name: " + storeName);
		            System.out.println("Location: " + location);
		            System.out.println("Phone: " + phone);
		            System.out.println("-------------------------------------------------");
		        } else {
		            System.out.println("Store not found for the given owner ID.");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

}
