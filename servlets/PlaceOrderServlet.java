package com.myfoods.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.myfoods.model.CartItem;
import com.myfoods.utility.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PlaceOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String paymentMode = request.getParameter("paymentMode");
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));

        // Retrieve order details from the session or request attributes
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null && !cart.getItems().isEmpty()) {
            // Process the order and retrieve order details
            Map<String, Map<String, String>> orderDetails = new HashMap<>();
            for (CartItem item : cart.getItems().values()) {
                Map<String, String> itemDetails = new HashMap<>();
                itemDetails.put("quantity", String.valueOf(item.getQuantity()));
                itemDetails.put("price", String.valueOf(item.getPrice()));
                orderDetails.put(item.getName(), itemDetails);
            }

            // Set order details as request attributes
            request.setAttribute("orderDetails", orderDetails);
        }

        // Set customer and shipping information as request attributes
        request.setAttribute("customerName", name);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.setAttribute("paymentMode", paymentMode);
        request.setAttribute("totalAmount", totalAmount);

        // Forward to orders.jsp
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }
}
