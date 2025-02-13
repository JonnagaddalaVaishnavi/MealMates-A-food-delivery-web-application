package com.myfoods.servlets;

import java.io.IOException;

import com.myfoods.daoimple.MenuDAOImplementation;
import com.myfoods.model.CartItem;
import com.myfoods.model.Menu;
import com.myfoods.utility.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private CartItem item;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        int newRestaurantId = Integer.parseInt(req.getParameter("restaurant_id"));
        Integer currentRestaurantID = (Integer) session.getAttribute("restaurant_id");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
            session.setAttribute("restaurant_id", newRestaurantId);
        } else if (currentRestaurantID != null && newRestaurantId != currentRestaurantID) {
            cart.clear(); // Clear the cart if the restaurant ID changes
            session.setAttribute("restaurant_id", newRestaurantId); // Update the restaurantId
        }

        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                addItemToCart(req, cart);
            } else if ("update".equals(action)) {
                updateItemInCart(req, cart);
            } else if ("remove".equals(action)) {
                removeItemFromCart(req, cart);
            } else if ("clear".equals(action)) {
                cart.clear();
                System.out.println("Cart cleared.");
            }

            System.out.println("Action: " + action + " completed."); // Debugging
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect back to the cart page
        resp.sendRedirect("cart.jsp");
    }

    private void addItemToCart(HttpServletRequest req, Cart cart) throws ClassNotFoundException {
        String itemIdParam = req.getParameter("menu_id");
        String qtyParam = req.getParameter("quantity");

        if (itemIdParam != null && qtyParam != null) {
            int itemId = Integer.parseInt(itemIdParam);
            int quantity = Integer.parseInt(qtyParam);

            MenuDAOImplementation menuDAO = new MenuDAOImplementation();
            Menu menuItem = menuDAO.getMenu(itemId);

            if (menuItem != null) {
                item = new CartItem(menuItem.getMenuId(),
                                    menuItem.getItemName(),
                                    menuItem.getPrice(),
                                    quantity,
                                    menuItem.getRestaurantId(),
                                    menuItem.getImagePath());

                cart.addItem(item);
            }
        }
    }

    private void updateItemInCart(HttpServletRequest req, Cart cart) throws ClassNotFoundException {
        String itemIdParam = req.getParameter("menu_id");
        String qtyParam = req.getParameter("quantity");

        if (itemIdParam != null && qtyParam != null) {
            int itemId = Integer.parseInt(itemIdParam);
            int quantity = Integer.parseInt(qtyParam);

            System.out.println("Updating item ID: " + itemId + " with quantity: " + quantity); // Debug log

            cart.updateItem(itemId, quantity);
        } else {
            System.out.println("Item ID or quantity is null"); // Debugging log
        }
    }

    private void removeItemFromCart(HttpServletRequest req, Cart cart) throws ClassNotFoundException {
        String itemIdParam = req.getParameter("menu_id");

        if (itemIdParam != null) {
            int itemId = Integer.parseInt(itemIdParam);
            cart.removeItem(itemId);
        }
    }
}
