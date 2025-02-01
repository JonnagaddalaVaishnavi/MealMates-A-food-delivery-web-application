package com.myfoods.testclas;

import java.util.List;
import java.util.Scanner;

import com.myfoods.dao.MenuDAO;
import com.myfoods.daoimple.MenuDAOImplementation;
import com.myfoods.model.Menu;

public class TestMenuDAO {
    public static void main(String[] args) {
        MenuDAO menuDAO = new MenuDAOImplementation();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Menu Management ---");
            System.out.println("1. Add Menu");
            System.out.println("2. Get Menu");
            System.out.println("3. Update Menu");
            System.out.println("4. Delete Menu");
            System.out.println("5. Get All Menus by Restaurant");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Menu
                    System.out.println("\n--- Add Menu ---");
                    Menu newMenu = new Menu();
                    System.out.print("Enter Item Name: ");
                    newMenu.setItemName(scanner.nextLine());
                    System.out.print("Enter Description: ");
                    newMenu.setDescription(scanner.nextLine());
                    System.out.print("Enter Price: ");
                    newMenu.setPrice(scanner.nextInt());
                    System.out.print("Enter Ratings (0.0-5.0): ");
                    newMenu.setRatings(scanner.nextFloat());
                    System.out.print("Is Available (true/false): ");
                    newMenu.setAvailable(scanner.nextBoolean());
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Image Path: ");
                    newMenu.setImagePath(scanner.nextLine());

                    menuDAO.addMenu(newMenu);
                    System.out.println("Menu added successfully!");
                    break;

                case 2:
                    // Get Menu
                    System.out.print("\nEnter Menu ID to fetch details: ");
                    int menuId = scanner.nextInt();
                    Menu fetchedMenu = menuDAO.getMenu(menuId);
                    if (fetchedMenu != null) {
                        System.out.println("\n--- Menu Details ---");
                        System.out.println("ID: " + fetchedMenu.getMenuId());
                        System.out.println("Item Name: " + fetchedMenu.getItemName());
                        System.out.println("Description: " + fetchedMenu.getDescription());
                        System.out.println("Price: " + fetchedMenu.getPrice());
                        System.out.println("Ratings: " + fetchedMenu.getRatings());
                        System.out.println("Available: " + fetchedMenu.isAvailable());
                        System.out.println("Image Path: " + fetchedMenu.getImagePath());
                    } else {
                        System.out.println("Menu not found!");
                    }
                    break;

                case 3:
                    // Update Menu
                    System.out.println("\n--- Update Menu ---");
                    Menu updateMenu = new Menu();
                    System.out.print("Enter Menu ID to update: ");
                    updateMenu.setMenuId(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Item Name: ");
                    updateMenu.setItemName(scanner.nextLine());
                    System.out.print("Enter New Description: ");
                    updateMenu.setDescription(scanner.nextLine());
                    System.out.print("Enter New Price: ");
                    updateMenu.setPrice(scanner.nextInt());
                    System.out.print("Enter New Ratings (0.0-5.0): ");
                    updateMenu.setRatings(scanner.nextFloat());
                    System.out.print("Is Available (true/false): ");
                    updateMenu.setAvailable(scanner.nextBoolean());
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Image Path: ");
                    updateMenu.setImagePath(scanner.nextLine());

                    menuDAO.updateMenu(updateMenu);
                    System.out.println("Menu updated successfully!");
                    break;

                case 4:
                    // Delete Menu
                    System.out.print("\nEnter Menu ID to delete: ");
                    int deleteMenuId = scanner.nextInt();
                    menuDAO.deleteMenu(deleteMenuId);
                    System.out.println("Menu deleted successfully!");
                    break;

                case 5:
                    // Get All Menus by Restaurant
                    System.out.print("\nEnter Restaurant ID to fetch all menus: ");
                    int restaurantId = scanner.nextInt();
                    List<Menu> menus = menuDAO.getAllMenusByRestaurant(restaurantId);
                    System.out.println("ID | Item Name | Description | Price | Ratings | Available | Image Path");
                    System.out.println("-----------------------------------------------------------------------");
                    for (Menu menu : menus) {
                        System.out.println(
                            menu.getMenuId() + " | " +
                            menu.getItemName() + " | " +
                            menu.getDescription() + " | " +
                            menu.getPrice() + " | " +
                            menu.getRatings() + " | " +
                            menu.isAvailable() + " | " +
                            menu.getImagePath()
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
