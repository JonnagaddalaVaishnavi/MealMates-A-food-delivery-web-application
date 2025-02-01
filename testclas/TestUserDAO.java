package com.myfoods.testclas;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.myfoods.dao.UserDAO;
import com.myfoods.daoimple.UserDAOImplementation;
import com.myfoods.model.User;

public class TestUserDAO {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImplementation();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- User Management ---");
            System.out.println("1. Add User");
            System.out.println("2. Get User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Get All Users");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    // Add User
                    System.out.println("\n--- Add User ---");
                    User newUser = new User();
                    System.out.print("Enter Name: ");
                    newUser.setName(scanner.nextLine());
                    System.out.print("Enter Username: ");
                    newUser.setUsername(scanner.nextLine());
                    System.out.print("Enter Password: ");
                    newUser.setPassword(scanner.nextLine());
                    System.out.print("Enter Email: ");
                    newUser.setEmail(scanner.nextLine());
                    System.out.print("Enter Phone: ");
                    newUser.setPhone(scanner.nextLine());
                    System.out.print("Enter Address: ");
                    newUser.setAddress(scanner.nextLine());
                    System.out.print("Enter Role: ");
                    newUser.setRole(scanner.nextLine());
                    newUser.setCreatedDate(new Date());
                    userDAO.addUser(newUser);
                    System.out.println("User added successfully!");
                    break;

                case 2:
                    // Get User
                    System.out.print("\nEnter User ID to fetch details: ");
                    int userId = scanner.nextInt();
                    User fetchedUser = userDAO.getUser(userId);
                    if (fetchedUser != null) {
                        System.out.println("\n--- User Details ---");
                        System.out.println("ID: " + fetchedUser.getUserId());
                        System.out.println("Name: " + fetchedUser.getName());
                        System.out.println("Username: " + fetchedUser.getUsername());
                        System.out.println("Email: " + fetchedUser.getEmail());
                        System.out.println("Phone: " + fetchedUser.getPhone());
                        System.out.println("Address: " + fetchedUser.getAddress());
                        System.out.println("Role: " + fetchedUser.getRole());
                    } else { 
                    	System.out.println("User not found!"); 
                    }
                    break;

                case 3:
                    // Update User
                    System.out.println("\n--- Update User ---");
                    User updateUser = new User();
                    System.out.print("Enter User ID to update: ");
                    updateUser.setUserId(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Name: ");
                    updateUser.setName(scanner.nextLine());
                    System.out.print("Enter New Password: ");
                    updateUser.setPassword(scanner.nextLine());
                    System.out.print("Enter New Phone: ");
                    updateUser.setPhone(scanner.nextLine());
                    System.out.print("Enter New Address: ");
                    updateUser.setAddress(scanner.nextLine());
                    System.out.print("Enter New Role: ");
                    updateUser.setRole(scanner.nextLine());
                    userDAO.updateUser(updateUser);
                    System.out.println("User updated successfully!");
                    break;

                case 4:
                    // Delete User
                    System.out.print("\nEnter User ID to delete: ");
                    int deleteUserId = scanner.nextInt();
                    userDAO.deleteUser(deleteUserId);
                    System.out.println("User deleted successfully!");
                    break;
                    
                case 5:
                    // Get All Users
                    System.out.println("\n--- All Users ---");
                    List<User> users = userDAO.getAllUsers();
                    for (User user : users) {
                        System.out.println(user.getUserId() + " | " + 
                        				   user.getName() + " | " + 
                        		           user.getUsername() + " | " + 
                        				   user.getEmail() + " | " + 
                        		           user.getPhone() + " | " + 
                        				   user.getAddress() + " | " + 
                        		           user.getRole());
                    }
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting the application...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);

        scanner.close();            
    }
}
