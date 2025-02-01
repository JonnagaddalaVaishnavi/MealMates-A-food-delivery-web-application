package com.myfoods.testclas;

import java.util.List;
import java.util.Scanner;

import com.myfoods.dao.OrderItemDAO;
import com.myfoods.daoimple.OrderItemDAOImplementation;
import com.myfoods.model.OrderItem;

public class TestOrderItemDAO {
    public static void main(String[] args) {
        OrderItemDAO orderItemDAO = new OrderItemDAOImplementation();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Order Item Management ---");
            System.out.println("1. Add Order Item");
            System.out.println("2. Get Order Item");
            System.out.println("3. Update Order Item");
            System.out.println("4. Delete Order Item");
            System.out.println("5. Get All Order Items by Order");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Order Item
                    System.out.println("\n--- Add Order Item ---");
                    OrderItem newOrderItem = new OrderItem();
                    System.out.print("Enter Quantity: ");
                    newOrderItem.setQuantity(scanner.nextInt());
                    System.out.print("Enter Total Price: ");
                    newOrderItem.setTotalPrice(scanner.nextInt());
                    scanner.nextLine(); // Consume newline

                    orderItemDAO.addOrderItem(newOrderItem);
                    System.out.println("Order Item added successfully!");
                    break;

                case 2:
                    // Get Order Item
                    System.out.print("\nEnter Order Item ID to fetch details: ");
                    int orderItemId = scanner.nextInt();
                    OrderItem fetchedOrderItem = orderItemDAO.getOrderItem(orderItemId);
                    if (fetchedOrderItem != null) {
                        System.out.println("\n--- Order Item Details ---");
                        System.out.println("Order Item ID: " + fetchedOrderItem.getOrderItemId());
                        System.out.println("Order ID: " + fetchedOrderItem.getOrderId());
                        System.out.println("Menu ID: " + fetchedOrderItem.getMenuId());
                        System.out.println("Quantity: " + fetchedOrderItem.getQuantity());
                        System.out.println("Total Price: " + fetchedOrderItem.getTotalPrice());
                    } else {
                        System.out.println("Order Item not found!");
                    }
                    break;

                case 3:
                    // Update Order Item
                    System.out.println("\n--- Update Order Item ---");
                    OrderItem updateOrderItem = new OrderItem();
                    System.out.print("Enter Order Item ID to update: ");
                    updateOrderItem.setOrderItemId(scanner.nextInt());
                    System.out.print("Enter Quantity: ");
                    updateOrderItem.setQuantity(scanner.nextInt());
                    System.out.print("Enter Total Price: ");
                    updateOrderItem.setTotalPrice(scanner.nextInt());
                    scanner.nextLine(); // Consume newline

                    orderItemDAO.updateOrderItem(updateOrderItem);
                    System.out.println("Order Item updated successfully!");
                    break;

                case 4:
                    // Delete Order Item
                    System.out.print("\nEnter Order Item ID to delete: ");
                    int deleteOrderItemId = scanner.nextInt();
                    orderItemDAO.deleteOrderItem(deleteOrderItemId);
                    System.out.println("Order Item deleted successfully!");
                    break;

                case 5:
                    // Get All Order Items by Order
                    System.out.print("\nEnter Order ID to fetch all order items: ");
                    int orderId = scanner.nextInt();
                    List<OrderItem> orderItems = orderItemDAO.getAllOrderItemsByOrder(orderId);
                    System.out.println("Order Item ID | Order ID | Menu ID | Quantity | Total Price");
                    System.out.println("----------------------------------------------------------");
                    for (OrderItem orderItem : orderItems) {
                        System.out.println(
                            orderItem.getOrderItemId() + " | " +
                            orderItem.getOrderId() + " | " +
                            orderItem.getMenuId() + " | " +
                            orderItem.getQuantity() + " | " +
                            orderItem.getTotalPrice()
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
