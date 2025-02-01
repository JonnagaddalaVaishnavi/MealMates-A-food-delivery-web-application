package com.myfoods.testclas;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.myfoods.dao.OrderDAO;
import com.myfoods.daoimple.OrderDAOImplementation;
import com.myfoods.model.Order;

public class TestOrderDAO {
    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAOImplementation();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Order Management ---");
            System.out.println("1. Add Order");
            System.out.println("2. Get Order");
            System.out.println("3. Update Order");
            System.out.println("4. Delete Order");
            System.out.println("5. Get All Orders by User");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Order
                    System.out.println("\n--- Add Order ---");
                    Order newOrder = new Order();
                    System.out.print("Enter User ID: ");
                    newOrder.setUserId(scanner.nextInt());
                    System.out.print("Enter Restaurant ID: ");
                    newOrder.setRestaurantId(scanner.nextInt());
                    System.out.print("Enter Order Date (YYYY-MM-DD): ");
                    newOrder.setOrderDate(Date.valueOf(scanner.next()));
                    System.out.print("Enter Total Amount: ");
                    newOrder.setTotalAmount(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Status: ");
                    newOrder.setStatus(scanner.nextLine());
                    System.out.print("Enter Payment Mode: ");
                    newOrder.setPaymentMode(scanner.nextLine());

                    orderDAO.addOrder(newOrder);
                    System.out.println("Order added successfully!");
                    break;

                case 2:
                    // Get Order
                    System.out.print("\nEnter Order ID to fetch details: ");
                    int orderId = scanner.nextInt();
                    Order fetchedOrder = orderDAO.getOrder(orderId);
                    if (fetchedOrder != null) {
                        System.out.println("\n--- Order Details ---");
                        System.out.println("Order ID: " + fetchedOrder.getOrderId());
                        System.out.println("User ID: " + fetchedOrder.getUserId());
                        System.out.println("Restaurant ID: " + fetchedOrder.getRestaurantId());
                        System.out.println("Order Date: " + fetchedOrder.getOrderDate());
                        System.out.println("Total Amount: " + fetchedOrder.getTotalAmount());
                        System.out.println("Status: " + fetchedOrder.getStatus());
                        System.out.println("Payment Mode: " + fetchedOrder.getPaymentMode());
                    } else {
                        System.out.println("Order not found!");
                    }
                    break;

                case 3:
                    // Update Order
                    System.out.println("\n--- Update Order ---");
                    Order updateOrder = new Order();
                    System.out.print("Enter Order ID to update: ");
                    updateOrder.setOrderId(scanner.nextInt());
                    System.out.print("Enter User ID: ");
                    updateOrder.setUserId(scanner.nextInt());
                    System.out.print("Enter Restaurant ID: ");
                    updateOrder.setRestaurantId(scanner.nextInt());
                    System.out.print("Enter Order Date (YYYY-MM-DD): ");
                    updateOrder.setOrderDate(Date.valueOf(scanner.next()));
                    System.out.print("Enter Total Amount: ");
                    updateOrder.setTotalAmount(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Status: ");
                    updateOrder.setStatus(scanner.nextLine());
                    System.out.print("Enter Payment Mode: ");
                    updateOrder.setPaymentMode(scanner.nextLine());

                    orderDAO.updateOrder(updateOrder);
                    System.out.println("Order updated successfully!");
                    break;

                case 4:
                    // Delete Order
                    System.out.print("\nEnter Order ID to delete: ");
                    int deleteOrderId = scanner.nextInt();
                    orderDAO.deleteOrder(deleteOrderId);
                    System.out.println("Order deleted successfully!");
                    break;

                case 5:
                    // Get All Orders by User
                    System.out.print("\nEnter User ID to fetch all orders: ");
                    int userId = scanner.nextInt();
                    List<Order> orders = orderDAO.getAllOrdersByUser(userId);
                    System.out.println("Order ID | User ID | Restaurant ID | Order Date | Total Amount | Status | Payment Mode");
                    System.out.println("----------------------------------------------------------------------------");
                    for (Order order : orders) {
                        System.out.println(
                            order.getOrderId() + " | " +
                            order.getUserId() + " | " +
                            order.getRestaurantId() + " | " +
                            order.getOrderDate() + " | " +
                            order.getTotalAmount() + " | " +
                            order.getStatus() + " | " +
                            order.getPaymentMode()
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
