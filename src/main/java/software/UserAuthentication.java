package software;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthentication {
	public static  boolean isUserLoggedIn = false;
   public static boolean authenticate(String username, String password) {
    String query = "SELECT idusers, user_name, password FROM users WHERE user_name = ? AND password = ?";
    
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) { // If a record is found, authentication is successful
            isUserLoggedIn = true;
            return true;
        } else {
            return false;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    
    public static boolean signup(String username, String password, String role, String city) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            
            String checkUserQuery = "SELECT * FROM Users WHERE user_name = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkUserQuery);
            checkStatement.setString(1, username);
            ResultSet checkResultSet = checkStatement.executeQuery();

            if (checkResultSet.next()) {
                System.out.println("Username already exists. Please choose a different username.");
                return false;
            }

            // Insert new user into the database
            String insertQuery = "INSERT INTO Users (user_name, password, role, city) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            insertStatement.setString(3, role);
            insertStatement.setString(4, city);
            insertStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUserRole(String username) {
        String role = null;

        // Establish a connection to the database
        try (Connection connection = DatabaseConnection.getConnection()) {
            // SQL query to get the role of the user
            String query = "SELECT role FROM Users WHERE user_name = ?";
            
            // Prepare the statement to avoid SQL injection
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // If a result is found, retrieve the role
            if (resultSet.next()) {
                role = resultSet.getString("role");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching the user role.");
        }

        return role;
    }
    public static int getUserId(String loginUsername) {
        int userId = -1; // Default value if the user is not found
        String query = "SELECT idusers FROM users WHERE user_name = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, loginUsername);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                userId = resultSet.getInt("idusers");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return userId;
    }
    public static UserDetails getUserDetails(String username) {
        UserDetails userDetails = null;
        String query = "SELECT user_name, city FROM users WHERE user_name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the username parameter
            statement.setString(1, username);
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            
            // Process the result
            if (resultSet.next()) {
                String city = resultSet.getString("city");
                userDetails = new UserDetails(username, city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return userDetails;
    }
    public static boolean updatePassword(String username, String newPassword) {
        String query = "UPDATE users SET password = ? WHERE user_name = ?";
        

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the parameters for the query
            statement.setString(1, newPassword);
            statement.setString(2, username);
            
            // Execute the update
            int rowsAffected = statement.executeUpdate();
            
            // Return true if one or more rows were updated
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updateCity(String username, String newCity) {
        String query = "UPDATE users SET city = ? WHERE user_name = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the parameters for the query
            statement.setString(1, newCity);
            statement.setString(2, username);
            
            // Execute the update
            int rowsAffected = statement.executeUpdate();
            
            // Return true if one or more rows were updated
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void updateOwnerAccountDetails(int ownerId, String newCity, String newPassword) {
        String query = "UPDATE users SET city = ?, password = ? WHERE idusers = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newCity);
            statement.setString(2, newPassword);
            statement.setInt(3, ownerId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Account details updated successfully.");
            } else {
                System.out.println("Failed to update account details. User not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStoreInformation(int ownerId, String newStoreName, String newLocation, String newPhone) {
        String query = "UPDATE stores SET name = ?, address = ?, phone = ?  WHERE owner_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newStoreName);
            statement.setString(2, newLocation);
            statement.setString(3, newPhone);
            statement.setInt(4, ownerId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Store information updated successfully.");
            } else {
                System.out.println("Failed to update store information. Store not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	

}
