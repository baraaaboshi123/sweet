package software;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;
public class BeneficiaryUserDashboard {

    public static void postAndShareDessertCreation(String username, String dessertDetails) {
        String query = "INSERT INTO dessert_creations (user_name, details) VALUES (?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the parameters for the query
            statement.setString(1, username);
            statement.setString(2, dessertDetails);
            
            // Execute the insert
            int rowsAffected = statement.executeUpdate();
            
            // Check if the insert was successful
            if (rowsAffected > 0) {
                System.out.println("Dessert creation posted and shared successfully!");
            } else {
                System.out.println("Failed to post dessert creation.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listStoreOwners() {
        String query = "SELECT idusers, user_name FROM users WHERE role = 'StoreOwner'";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Available Store Owners:");
            while (resultSet.next()) {
                int ownerId = resultSet.getInt("idusers");
                String ownerUsername = resultSet.getString("user_name");
                System.out.println("Store Owner ID: " + ownerId + ", Username: " + ownerUsername);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void sendMessageToStoreOwner(int userId, int storeOwnerId, String message) {
        String query = "INSERT INTO messages (sender_id, receiver_id, message_content, sent_at) VALUES (?, ?, ?, NOW())";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId); // User's ID (sender)
            statement.setInt(2, storeOwnerId); // Store Owner's ID (receiver)
            statement.setString(3, message);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Message sent successfully!");
            } else {
                System.out.println("Failed to send message.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void viewInbox(int userId) {
        String query = "SELECT messages.message_content, messages.sent_at, users.idusers AS sender_id, users.user_name AS sender_name " +
                       "FROM messages " +
                       "JOIN users ON messages.sender_id = users.idusers " +
                       "WHERE messages.receiver_id = ? " +
                       "ORDER BY messages.sent_at DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No messages in your inbox.");
            } else {
                while (resultSet.next()) {
                    String content = resultSet.getString("message_content");
                    String createdAt = resultSet.getString("sent_at");
                    int senderId = resultSet.getInt("sender_id");
                    String senderName = resultSet.getString("sender_name");

                    System.out.println("From: " + senderName + " (ID: " + senderId + ")");
                    System.out.println("Date: " + createdAt);
                    System.out.println("Message: " + content);
                    System.out.println("--------------------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteOrder(int orderId) {
        String checkStatusQuery = "SELECT status FROM orders WHERE idorders = ?";
        String deleteOrderQuery = "DELETE FROM orders WHERE idorders = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             // First, check the order status
             PreparedStatement checkStatusStatement = connection.prepareStatement(checkStatusQuery)) {

            checkStatusStatement.setInt(1, orderId);
            ResultSet resultSet = checkStatusStatement.executeQuery();

            if (resultSet.next()) {
                String status = resultSet.getString("status");

                if ("placed".equalsIgnoreCase(status)) {
                    // Status is "placed", proceed with deletion
                    try (PreparedStatement deleteOrderStatement = connection.prepareStatement(deleteOrderQuery)) {
                        deleteOrderStatement.setInt(1, orderId);
                        int rowsAffected = deleteOrderStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Order canceled successfully.");
                        } else {
                            System.out.println("No order found with the specified ID.");
                        }
                    }
                } else {
                    System.out.println("Order cannot be canceled because it is not in 'placed' status.");
                }
            } else {
                System.out.println("No order found with the specified ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting order: " + e.getMessage());
        }
    }
    public static void listDessertCreations() {
        String query = "SELECT id, user_name, details FROM dessert_creations";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                String details = resultSet.getString("details");

                System.out.println("Dessert Creation ID: " + id);
                System.out.println("User Name: " + userName);
                System.out.println("Details: " + details);
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error listing dessert creations: " + e.getMessage());
        }
    }
    public static void listDessertCreationsByUser(String userName) {
        String query = "SELECT id, details FROM dessert_creations WHERE user_name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("No dessert creations found for user: " + userName);
                return;
            }

            System.out.println("Dessert Creations for User: " + userName);
            System.out.println("-------------------------------------------------");

            do {
                int id = resultSet.getInt("id");
                String details = resultSet.getString("details");

                System.out.println("Dessert Creation ID: " + id);
                System.out.println("Details: " + details);
                System.out.println("-------------------------------------------------");
            } while (resultSet.next());

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error listing dessert creations for user: " + e.getMessage());
        }
    }
    public static void deleteDessertCreationById(int id) {
        String query = "DELETE FROM dessert_creations WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dessert creation with ID " + id + " deleted successfully.");
            } else {
                System.out.println("No dessert creation found with ID " + id + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting dessert creation: " + e.getMessage());
        }
    }

}
