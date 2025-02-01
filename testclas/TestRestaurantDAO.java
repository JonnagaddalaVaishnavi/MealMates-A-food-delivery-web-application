package com.myfoods.testclas;

import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import com.myfoods.dao.RestaurantDAO;
import com.myfoods.daoimple.RestaurantDAOImplementation;
import com.myfoods.model.Restaurant;

public class TestRestaurantDAO {
	 public static void main(String[] args) {
	        RestaurantDAO restaurantDAO = new RestaurantDAOImplementation();
	        Scanner scanner = new Scanner(System.in);
	        int choice;

	        do {
	            System.out.println("\n--- Restaurant Management ---");
	            System.out.println("1. Add Restaurant");
	            System.out.println("2. Get Restaurant");
	            System.out.println("3. Update Restaurant");
	            System.out.println("4. Delete Restaurant");
	            System.out.println("5. Get All Restaurants");
	            System.out.println("6. Exit");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                    // Add Restaurant
	                    System.out.println("\n--- Add Restaurant ---");
	                    Restaurant newRestaurant = new Restaurant();
	                    System.out.print("Enter Name: ");
	                    newRestaurant.setName(scanner.nextLine());
	                    System.out.print("Enter Address: ");
	                    newRestaurant.setAddress(scanner.nextLine());
	                    System.out.print("Enter Phone: ");
	                    newRestaurant.setPhone(scanner.nextLine());
	                    System.out.print("Enter Rating (0.0-5.0): ");
	                    newRestaurant.setRating(scanner.nextFloat());
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Cuisine Type: ");
	                    newRestaurant.setCusineType(scanner.nextLine());
	                    System.out.print("Is Active (true/false): ");
	                    newRestaurant.setActive(scanner.nextBoolean());
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter ETA (HH:mm:ss): ");
	                    String etaStr = scanner.nextLine();
	                    newRestaurant.setEta(Time.valueOf(etaStr));
	                    System.out.print("Enter Image Path: ");
	                    newRestaurant.setImagePath(scanner.nextLine());
	                    
	                    restaurantDAO.addRestaurant(newRestaurant);
	                    System.out.println("Restaurant added successfully!");
	                    break;

	                case 2:
	                    // Get Restaurant
	                    System.out.print("\nEnter Restaurant ID to fetch details: ");
	                    int restaurantId = scanner.nextInt();
	                    Restaurant fetchedRestaurant = restaurantDAO.getRestaurant(restaurantId);
	                    if (fetchedRestaurant != null) {
	                        System.out.println("\n--- Restaurant Details ---");
	                        System.out.println("ID: " + fetchedRestaurant.getRestaurantId());
	                        System.out.println("Name: " + fetchedRestaurant.getName());
	                        System.out.println("Address: " + fetchedRestaurant.getAddress());
	                        System.out.println("Phone: " + fetchedRestaurant.getPhone());
	                        System.out.println("Rating: " + fetchedRestaurant.getRating());
	                        System.out.println("Cuisine Type: " + fetchedRestaurant.getCuisineType());
	                        System.out.println("Active: " + fetchedRestaurant.isActive());
	                        System.out.println("ETA: " + fetchedRestaurant.getEta());
	                        System.out.println("Image Path: " + fetchedRestaurant.getImagePath());
	                    } else {
	                        System.out.println("Restaurant not found!");
	                    }
	                    break;

	                case 3:
	                    // Update Restaurant
	                    System.out.println("\n--- Update Restaurant ---");
	                    Restaurant updateRestaurant = new Restaurant();
	                    System.out.print("Enter Restaurant ID to update: ");
	                    updateRestaurant.setRestaurantId(scanner.nextInt());
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter New Name: ");
	                    updateRestaurant.setName(scanner.nextLine());
	                    System.out.print("Enter New Address: ");
	                    updateRestaurant.setAddress(scanner.nextLine());
	                    System.out.print("Enter New Phone: ");
	                    updateRestaurant.setPhone(scanner.nextLine());
	                    System.out.print("Enter New Rating (0.0-5.0): ");
	                    updateRestaurant.setRating(scanner.nextFloat());
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter New Cuisine Type: ");
	                    updateRestaurant.setCusineType(scanner.nextLine());
	                    System.out.print("Is Active (true/false): ");
	                    updateRestaurant.setActive(scanner.nextBoolean());
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter New ETA (HH:mm:ss): ");
	                    String newEtaStr = scanner.nextLine();
	                    updateRestaurant.setEta(Time.valueOf(newEtaStr));
	                    System.out.print("Enter New Image Path: ");
	                    updateRestaurant.setImagePath(scanner.nextLine());
	                    
	                    restaurantDAO.updateRestaurant(updateRestaurant);
	                    System.out.println("Restaurant updated successfully!");
	                    break;

	                case 4:
	                    // Delete Restaurant
	                    System.out.print("\nEnter Restaurant ID to delete: ");
	                    int deleteRestaurantId = scanner.nextInt();
	                    restaurantDAO.deleteRestaurant(deleteRestaurantId);
	                    System.out.println("Restaurant deleted successfully!");
	                    break;

	                case 5:
	                    // Get All Restaurants
	                    System.out.println("\n--- All Restaurants ---");
	                    List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();
	                    System.out.println("ID | Name | Address | Phone | Rating | Cuisine Type | Active | ETA | Image Path");
	                    System.out.println("--------------------------------------------------------------------");
	                    for (Restaurant restaurant : restaurants) {
	                        System.out.println(
	                            restaurant.getRestaurantId() + " | " +
	                            restaurant.getName() + " | " +
	                            restaurant.getAddress() + " | " +
	                            restaurant.getPhone() + " | " +
	                            restaurant.getRating() + " | " +
	                            restaurant.getCuisineType() + " | " +
	                            restaurant.isActive() + " | " +
	                            restaurant.getEta() + " | " +
	                            restaurant.getImagePath()
	                        );
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
