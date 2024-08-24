// package software;

// import java.util.Scanner;

// public class Main {

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         boolean exit = false;

//         while (!exit) {
//             System.out.println("Welcome to Sweet Management System");

           
//             System.out.println("1. Login");
//             System.out.println("2. Sign Up");
//             System.out.println("3. Exit");
//             System.out.print("Choose an option (1, 2, or 3): ");
//             int choice = scanner.nextInt();
//             scanner.nextLine(); 

//             switch (choice) {
//                 case 1:
                  
//                     System.out.print("Enter username: ");
//                     String loginUsername = scanner.nextLine();

//                     System.out.print("Enter password: ");
//                     String loginPassword = scanner.nextLine();

//                     if (UserAuthentication.authenticate(loginUsername, loginPassword)) {
//                         System.out.println("Login successful!");

                        
//                         String role = UserAuthentication.getUserRole(loginUsername);

//                         if (role.equalsIgnoreCase("StoreOwner") || role.equalsIgnoreCase("RawMaterialSuppliers")) {
                           
//                             boolean storeOwnerExit = false;
//                             while (!storeOwnerExit) {
//                                 System.out.println("Store Owner | RawMaterialSupplier Dashboard");
//                                 System.out.println("1. Add Product");
//                                 System.out.println("2. Update Product");
//                                 System.out.println("3. Remove Product");
//                                 System.out.println("4. List Products");
//                                 System.out.println("5. Post Recipe");
//                                 System.out.println("6. Communicate with users");
//                                 System.out.println("7. Monitor Sales");
//                                 System.out.println("8. Identify Best Selling Products (Top 5)");
//                                 System.out.println("9. Implement Dynamic Discount");
//                                 System.out.println("10. Remove Discount");
//                                 System.out.println("11. Manage Orders");
//                                 System.out.println("12. Manage Account Details And Update Business Information");
//                                 System.out.println("13. Log out");
//                                 System.out.print("Choose an option (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 or 13): ");
//                                 int storeOwnerChoice = scanner.nextInt();
//                                 scanner.nextLine(); 

//                                 switch (storeOwnerChoice) {
//                                     case 1:
                                      
//                                         System.out.print("Enter product name: ");
//                                         String productName = scanner.nextLine();

//                                         System.out.print("Enter product price: ");
//                                         double productPrice = scanner.nextDouble();
//                                         scanner.nextLine();

//                                         int ownerId = UserAuthentication.getUserId(loginUsername);

//                                         ProductManagement.addProduct(productName, productPrice, ownerId);
//                                         break;

//                                     case 2:
                                     
//                                         System.out.print("Enter product ID to update: ");
//                                         int productId = scanner.nextInt();
//                                         scanner.nextLine();

//                                         System.out.print("Enter new product name: ");
//                                         String newProductName = scanner.nextLine();

//                                         System.out.print("Enter new product price: ");
//                                         double newProductPrice = scanner.nextDouble();
//                                         scanner.nextLine(); 

//                                         ProductManagement.updateProduct(productId, newProductName, newProductPrice);
//                                         break;

//                                     case 3:
                                     
//                                         System.out.print("Enter product ID to remove: ");
//                                         int removeProductId = scanner.nextInt();
//                                         scanner.nextLine(); 

//                                         ProductManagement.removeProduct(removeProductId);
//                                         break;

//                                     case 4:
                                        
//                                         int ownerId1 = UserAuthentication.getUserId(loginUsername);
//                                         ProductManagement.listProducts(ownerId1);
//                                         break;
//                                     case 5:
                                        
//                                         System.out.print("Enter the name of your recipe: ");
//                                         String recipeName = scanner.nextLine();

//                                         System.out.print("Enter a description of the recipe: ");
//                                         String recipeDescription = scanner.nextLine();

//                                         System.out.print("Enter the ingredients: ");
//                                         String recipeIngredients = scanner.nextLine();

//                                         System.out.print("Enter any dietary information : ");
//                                         String dietaryInfo = scanner.nextLine();

//                                         int userId = UserAuthentication.getUserId(loginUsername);

//                                         ProductManagement.postRecipe(userId, recipeName, recipeDescription, recipeIngredients,  dietaryInfo);
//                                         break;
//                                     case 6:
//                                         // Communication options
//                                         System.out.println("1. Send a message to a user");
//                                         System.out.println("2. View your inbox");
//                                         System.out.print("Choose an option (1 or 2): ");
//                                         int communicationChoice = scanner.nextInt();
//                                         scanner.nextLine(); // Consume the newline character

//                                         switch (communicationChoice) {
//                                             case 1:
//                                                 // List all store owners
//                                                 BeneficiaryUserDashboard.listStoreOwners();

//                                                 // Select a store owner to communicate with
//                                                 System.out.print("Enter user ID to send a message: ");
//                                                 int storeOwnerId = scanner.nextInt();
//                                                 scanner.nextLine(); // Consume the newline character

//                                                 // Enter the message
//                                                 System.out.print("Enter your message: ");
//                                                 String message = scanner.nextLine();

//                                                 int userId1 = UserAuthentication.getUserId(loginUsername);
//                                                 BeneficiaryUserDashboard.sendMessageToStoreOwner(userId1, storeOwnerId, message); // Send the message
//                                                 break;

//                                             case 2:
//                                                 // View inbox
//                                             	int userId2 = UserAuthentication.getUserId(loginUsername);
//                                                 System.out.println("Your Inbox:");
//                                                 BeneficiaryUserDashboard.viewInbox(userId2); // Display the inbox
//                                                 break;

//                                             default:
//                                                 System.out.println("Invalid choice. Please select 1 or 2.");
//                                         }
//                                         break;
//                                     case 7:
//                                     	 int userId4 = UserAuthentication.getUserId(loginUsername);
//                                     	ProductManagement.monitorSales(userId4);
//                                         break;
//                                     case 8:
//                                    	 int userId5 = UserAuthentication.getUserId(loginUsername);
//                                    	ProductManagement.identifyBestSellingProducts(userId5);
//                                        break;
//                                     case 9:
//                                     	 System.out.println("Enter product ID to apply discount:");
//                                     	    int productId1 = scanner.nextInt();

//                                     	    System.out.println("Enter discount percentage:");
//                                     	    double discountPercentage = scanner.nextDouble();

//                                     	    ProductManagement.applyDiscount(productId1, discountPercentage);

//                                     	    double finalPrice =ProductManagement.calculateFinalPrice(productId1);
//                                     	    System.out.println("Final price after discount: $" + finalPrice);
//                                           break;
//                                     case 10:
//                                    	 System.out.println("Enter product ID to remove discount:");
//                                    	    int productId2 = scanner.nextInt();

                                   	    

//                                    	    ProductManagement.removeDiscount(productId2);

//                                    	    double finalPrice1 =ProductManagement.calculateFinalPrice(productId2);
//                                    	    System.out.println("Final price after remove discount: $" + finalPrice1);
//                                          break;
//                                     case 11:
//                                     	 int userId33 = UserAuthentication.getUserId(loginUsername);
//                                     	ProductManagement.listStoreOrders(userId33);
//                                         System.out.print("Enter the Order ID you want to manage: ");
//                                         int orderId = scanner.nextInt();
//                                         scanner.nextLine();                                      
//                                         System.out.println("Enter the new status for the order (e.g., Placed, Processing, Delivered, Completed): ");
//                                         String newStatus = scanner.nextLine();
//                                         ProductManagement.updateOrderStatus(orderId, newStatus);
//                                         ProductManagement.listStoreOrders(userId33);
                                    	
//                                     	break;
//                                     case 12:
//                                     	int userId22 = UserAuthentication.getUserId(loginUsername);
//                                     	 System.out.println("What would you like to update?");
//                                          System.out.println("1. Account Details");
//                                          System.out.println("2. Business Information");
//                                          System.out.print("Enter your choice (1 or 2): ");
//                                          int choice33 = scanner.nextInt();
//                                          scanner.nextLine(); // Consume the newline

//                                          switch (choice33) {
//                                              case 1:
//                                                  // Update account details
//                                                  System.out.println("Update Account Details");
//                                                  System.out.print("Enter new city: ");
//                                                  String newCity = scanner.nextLine();
//                                                  System.out.print("Enter new password: ");
//                                                  String newPassword = scanner.nextLine();

//                                                  UserAuthentication.updateOwnerAccountDetails(userId22, newCity, newPassword);
//                                                  break;

//                                              case 2:
//                                                  // Update store information
//                                                  System.out.println("Update Business Information");
//                                                  System.out.print("Enter new store name: ");
//                                                  String newStoreName = scanner.nextLine();

//                                                  System.out.print("Enter new store address: ");
//                                                  String newLocation = scanner.nextLine();
//                                                  System.out.print("Enter new store phone: ");
//                                                  String newPhone = scanner.nextLine();

//                                                  UserAuthentication.updateStoreInformation(userId22, newStoreName, newLocation,newPhone);
//                                                  ProductManagement.printStoreInformation(userId22);
//                                                  break;

//                                              default:
//                                                  System.out.println("Invalid choice. Please select either 1 or 2.");
//                                                  break;
//                                          }
                                        
//                                         break;

//                                     case 13:
                                       
//                                         storeOwnerExit = true;
//                                         System.out.println("Logged out successfully.");
//                                         break;

//                                     default:
//                                         System.out.println("Invalid choice. Please choose a valid option.");
//                                 }
//                             }
//                         } else if (role.equalsIgnoreCase("BeneficiaryUser")) {
                           
//                             boolean beneficiaryUserExit = false;
//                             while (!beneficiaryUserExit) {
//                                 System.out.println("Beneficiary User Dashboard");
//                                 System.out.println("1. Manage Personal Account");
//                                 System.out.println("2. Post and Share Dessert Creations");
//                                 System.out.println("3. Browse Recipes");
//                                 System.out.println("4. Filter Recipes");
//                                 System.out.println("5. Purchase Desserts");
//                                 System.out.println("6. Communicate with Store Owners and Suppliers");
//                                 System.out.println("7. Provide Feedback");
//                                 System.out.println("8. My Cart");
//                                 System.out.println("9. Browse Dessert Creactions Posts");
//                                 System.out.println("10. Manage My Dessert Creations Posts");
//                                 System.out.println("11. Log out");
//                                 System.out.print("Choose an option (1-11): ");
//                                 int beneficiaryUserChoice = scanner.nextInt();
//                                 scanner.nextLine(); 

//                                 switch (beneficiaryUserChoice) {
//                                 case 1:
                                    
//                                     boolean manageAccountExit = false;
//                                     while (!manageAccountExit) {
//                                         System.out.println("Manage Personal Account");
//                                         System.out.println("1. View Account Details");
//                                         System.out.println("2. Update Password");
//                                         System.out.println("3. Update City");
//                                         System.out.println("4. Back to Dashboard");
//                                         System.out.print("Choose an option (1-4): ");
//                                         int manageAccountChoice = scanner.nextInt();
//                                         scanner.nextLine();

//                                         switch (manageAccountChoice) {
//                                             case 1:
                                           
//                                                 UserDetails details = UserAuthentication.getUserDetails(loginUsername);
//                                                 System.out.println("Username: " + details.getUsername());
//                                                 System.out.println("City: " + details.getCity());
                                               
//                                                 break;

//                                             case 2:
                                             
//                                                 System.out.print("Enter new password: ");
//                                                 String newPassword = scanner.nextLine();
//                                                 boolean passwordUpdated = UserAuthentication.updatePassword(loginUsername, newPassword);
//                                                 if (passwordUpdated) {
//                                                     System.out.println("Password updated successfully.");
//                                                 } else {
//                                                     System.out.println("Failed to update password.");
//                                                 }
//                                                 break;

//                                             case 3:
                                               
//                                                 System.out.print("Enter new city: ");
//                                                 String newCity = scanner.nextLine();
//                                                 boolean cityUpdated = UserAuthentication.updateCity(loginUsername, newCity);
//                                                 if (cityUpdated) {
//                                                     System.out.println("City updated successfully.");
//                                                 } else {
//                                                     System.out.println("Failed to update city.");
//                                                 }
//                                                 break;

//                                             case 4:
                                               
//                                                 manageAccountExit = true;
//                                                 System.out.println("Returning to dashboard.");
//                                                 break;

//                                             default:
//                                                 System.out.println("Invalid choice. Please choose a valid option.");
//                                         }
//                                     }
//                                     break;

//                                 case 2:
                                    
//                                     System.out.print("Enter your dessert creation details: ");
//                                     String dessertDetails = scanner.nextLine();

                                    
//                                     BeneficiaryUserDashboard.postAndShareDessertCreation(loginUsername, dessertDetails);
//                                     break;

//                                 case 3:
                                  
//                                     System.out.println("Browsing Recipes:");
//                                     ProductManagement.browseRecipes(); 
//                                     break;

//                                     case 4:
                                    	 
//                                         System.out.print("Enter a search filter (or press Enter to see all recipes): ");
//                                         String filter = scanner.nextLine();
//                                         ProductManagement.browseRecipes(filter);
//                                         break;

//                                     case 5:
//                                         // List all products and allow purchase
//                                         System.out.println("Available Products:");
//                                         ProductManagement.listAllProducts();  // Call the method to display products

//                                         System.out.print("Enter dessert ID to purchase: ");
//                                         int dessertId = scanner.nextInt();
//                                         scanner.nextLine();

//                                         int buyerId = UserAuthentication.getUserId(loginUsername); // Get the logged-in user's ID
//                                         double finalPrice = ProductManagement.calculateFinalPrice(dessertId);
//                                         ProductManagement.purchaseProduct(buyerId, dessertId, finalPrice);  // Call the method to make a purchase
//                                         break;

//                                     case 6:
//                                         // Communication options
//                                         System.out.println("1. Send a message to a Store Owner");
//                                         System.out.println("2. View your inbox");
//                                         System.out.print("Choose an option (1 or 2): ");
//                                         int communicationChoice = scanner.nextInt();
//                                         scanner.nextLine(); // Consume the newline character
//                                         int userId = UserAuthentication.getUserId(loginUsername);

//                                         switch (communicationChoice) {
//                                             case 1:
//                                                 // List all store owners
//                                                 BeneficiaryUserDashboard.listStoreOwners();

//                                                 // Select a store owner to communicate with
//                                                 System.out.print("Enter Store Owner ID to send a message: ");
//                                                 int storeOwnerId = scanner.nextInt();
//                                                 scanner.nextLine(); // Consume the newline character

//                                                 // Enter the message
//                                                 System.out.print("Enter your message: ");
//                                                 String message = scanner.nextLine();

                                                
//                                                 BeneficiaryUserDashboard.sendMessageToStoreOwner(userId, storeOwnerId, message); // Send the message
//                                                 break;

//                                             case 2:
//                                                 // View inbox
//                                                 System.out.println("Your Inbox:");
//                                                 BeneficiaryUserDashboard.viewInbox(userId); // Display the inbox
//                                                 break;

//                                             default:
//                                                 System.out.println("Invalid choice. Please select 1 or 2.");
//                                         }
//                                         break;



//                                     case 7:
//                                         // Submit feedback for a product or a recipe
//                                         System.out.println("1. Leave feedback on a purchased product");
//                                         System.out.println("2. Leave feedback on a recipe");
//                                         System.out.print("Choose an option: ");
//                                         int feedbackChoice = scanner.nextInt();
//                                         scanner.nextLine();

//                                         int buyerId1 = UserAuthentication.getUserId(loginUsername); // Get the logged-in user's ID

//                                         if (feedbackChoice == 1) {
//                                             // List purchased products
//                                             ProductManagement.listPurchasedProducts(buyerId1);

//                                             System.out.print("Enter the Product ID to provide feedback: ");
//                                             int orderId = scanner.nextInt();
//                                             scanner.nextLine();

//                                             System.out.print("Enter your rating out of 5: ");
//                                             int productFeedback = scanner.nextInt();

//                                             ProductManagement.submitOrUpdateProductFeedback(buyerId1, orderId, productFeedback);
//                                         } else if (feedbackChoice == 2) {
//                                             // List all recipes
//                                             ProductManagement.browseRecipes();

//                                             System.out.print("Enter the Recipe ID to provide feedback: ");
//                                             int recipeId = scanner.nextInt();
//                                             scanner.nextLine();

//                                             System.out.print("Enter your feedback: ");
//                                             int recipeFeedback = scanner.nextInt();

//                                             ProductManagement.submitRecipeFeedback(buyerId1,recipeId, recipeFeedback);
//                                         } else {
//                                             System.out.println("Invalid choice.");
//                                         }
//                                         break;


//                                     case 8:
//                                     	int uid = UserAuthentication.getUserId(loginUsername);
//                                        ProductManagement.listPurchasedProducts(uid);
//                                        System.out.println("1. Cancle an order ");
//                                        System.out.println("2. Exit ");
//                                        int cc;
//                                        System.out.println("Enter your choice (1-2):");
//                                        cc=scanner.nextInt();
//                                        switch(cc) {
//                                        case 1:
//                                     	   System.out.println("Enter order ID:");
//                                     	   int oid= scanner.nextInt();
//                                            BeneficiaryUserDashboard.deleteOrder(oid);
//                                     	   break;
//                                        case 2: 
                                    	   
//                                     	   break;
//                                        }
                                      
                                      
//                                         break;
//                                     case 9:
                                        
//                                        BeneficiaryUserDashboard.listDessertCreations();
//                                         break;
//                                     case 10:
//                                     	BeneficiaryUserDashboard.listDessertCreationsByUser(loginUsername);
//                                     	System.out.println("1. Delete dessert creation post");
//                                     	System.out.println("2. Exit");
//                                     	System.out.println("Enter your choice(1-2):");
                                    	
//                                     	int c = scanner.nextInt();
//                                     	switch(c) {
//                                     	case 1:
//                                     		System.out.println("Enter post ID:");
//                                     		int pid=scanner.nextInt();
//                                     		BeneficiaryUserDashboard.deleteDessertCreationById(pid);
//                                     		break;
//                                     	case 2:
//                                     		break;
//                                     	}
                                    	
//                                     	break;
//                                     case 11:
                                        
//                                         beneficiaryUserExit = true;
//                                         System.out.println("Logged out successfully.");
//                                         break;

//                                     default:
//                                         System.out.println("Invalid choice. Please choose a valid option.");
//                                 }
//                             }
//                         }
//                         else if(role.equalsIgnoreCase("admin")) {
//                         	boolean adminExist = false;
//                         	while(!adminExist) {
//                         		 System.out.println("Admin Dashboard");
//                         		 System.out.println("1. Create Store Owner Account");
//                         	     System.out.println("2. Create Raw Material Supplier Account");
//                         	     System.out.println("3. Create Admin Account");
//                         	     System.out.println("4. Change Accounts Passwords");
//                         	     System.out.println("5. Monitor Sales Report");
//                         	     System.out.println("6. Best-Selling Products For Each Store");
//                                  System.out.println("7. List Users Group By Cities");
//                                  System.out.println("8. Manage Recipes And Posts");
//                                  System.out.println("9. Manage Feedback");
//                                  System.out.println("10. Log out");
//                                  System.out.print("Choose an option (1, 2, ,3, 4, 5,6, 7, 8, 9 or 10): ");
//                                  int storeOwnerChoice = scanner.nextInt();
//                                  scanner.nextLine(); 

//                                  switch (storeOwnerChoice) {
//                                  case 1:
//                                      role = "StoreOwner";
//                                      System.out.print("Enter a new username: ");
//                                      String signupUsername = scanner.nextLine();
//                                      System.out.print("Enter a new password: ");
//                                      String signupPassword = scanner.nextLine();
//                                      System.out.print("Enter your city: ");
//                                      String city = scanner.nextLine();
//                                      AdminDashboard.createUserAccount(signupUsername, city, signupPassword, role);
//                                      break;
//                                  case 2:
//                                      role = "RawMaterialSupplier";
//                                      System.out.print("Enter a new username: ");
//                                      String signupUsername1 = scanner.nextLine();
//                                      System.out.print("Enter a new password: ");
//                                      String signupPassword1 = scanner.nextLine();
//                                      System.out.print("Enter your city: ");
//                                      String city1 = scanner.nextLine();
//                                      AdminDashboard.createUserAccount(signupUsername1, city1, signupPassword1, role);
//                                      break;
//                                  case 3:
//                                      role = "admin";
//                                      System.out.print("Enter a new username: ");
//                                      String signupUsername2 = scanner.nextLine();
//                                      System.out.print("Enter a new password: ");
//                                      String signupPassword2 = scanner.nextLine();
//                                      System.out.print("Enter your city: ");
//                                      String city2 = scanner.nextLine();
//                                      AdminDashboard.createUserAccount(signupUsername2, city2, signupPassword2, role);
//                                      break;
//                                  case 4:
//                                     AdminDashboard.listAllUserAccounts();
//                                     System.out.print("Enter the ID of the user to change password: ");
//                                     int userId = scanner.nextInt();
//                                     scanner.nextLine(); 
//                                     System.out.print("Enter the new password: ");
//                                     String newPassword = scanner.nextLine();
//                                     AdminDashboard.changeUserPassword(userId, newPassword);
//                                     break;
//                                  case 5:
//                                    AdminDashboard.listAllStoresWithOwners();
//                                    System.out.print("Enter the ID of the owner to monitor store sales: ");
//                                    int ownerId1 = scanner.nextInt();
//                                    AdminDashboard.monitorSales(ownerId1);
//                                      break;
//                                  case 6:
//                                      AdminDashboard.identifyBestSellingProducts();
//                                       break;
//                                  case 7:
//                                      AdminDashboard.groupUsersByCity();
//                                       break;
//                                  case 8:
//                                    AdminDashboard.listAllRecipesAndPosts();
//                                    AdminDashboard.deleteContent();
//                                       break;
//                                  case 9:
//                                 	  System.out.println("1. List All Feedback");
//                                 	    System.out.println("2. Delete Feedback");
//                                 	    System.out.print("Choose an option: ");
//                                 	    int choice55 = scanner.nextInt();

//                                 	    switch (choice55) {
//                                 	        case 1:
//                                 	            AdminDashboard.listAllFeedback();
//                                 	            break;
//                                 	        case 2:
//                                 	            System.out.print("Enter the Feedback ID to delete: ");
//                                 	            int feedbackId = scanner.nextInt();
//                                 	            AdminDashboard.deleteFeedback(feedbackId);
//                                 	            break;
//                                 	        default:
//                                 	            System.out.println("Invalid option.");
//                                 	            break;
//                                 	    }
//                                       break;
//                                  case 10:
//                                      adminExist=true;
//                                      System.out.println("Logged out successfully.");
//                                       break;
//                                  default:
//                                      System.out.println("Invalid choice. Please select 1, 2, or 3.");
//                                      return;
                                 
//                                  }
//                         	}
//                         }
//                         else {
//                             System.out.println("Invalid role.");
//                         }
//                     } else {
//                         System.out.println("Login failed! Invalid username or password.");
//                     }
//                     break;

//                 case 2:
                	
//                 	 System.out.println("-------------Signup Form For BeneficiaryUser, StoreOwner and Store Owners and MaterialSuppliers-----------");
//                     System.out.print("Enter a new username: ");
//                     String signupUsername = scanner.nextLine();

//                     System.out.print("Enter a new password: ");
//                     String signupPassword = scanner.nextLine();

//                     System.out.print("Enter a role, choose one of these values (StoreOwner, BeneficiaryUser, RawMaterialSuppliers): ");
//                     String role = scanner.nextLine();

//                     System.out.print("Enter your city: ");
//                     String city = scanner.nextLine();
                    
//                     if(role.equalsIgnoreCase("Admin")) {
//                     	 System.out.println("you cant use Admin role");
//                     }
//                     else {
//                     	 if (UserAuthentication.signup(signupUsername, signupPassword, role, city)) {
//                              System.out.println("Signup successful! You can now log in.");
//                          } else {
//                              System.out.println("Signup failed! Please try again.");
//                          }
//                     }
                   
//                     break;

//                 case 3:
//                     // Exit the program
//                     System.out.println("Exiting the system. Goodbye!");
//                     exit = true;
//                     break;

//                 default:
//                     System.out.println("Invalid choice. Please choose a valid option.");
//             }
//         }

//         scanner.close();
//     }
// }
