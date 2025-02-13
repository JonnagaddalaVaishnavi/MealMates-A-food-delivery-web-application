package com.myfoods.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myfoods.model.OrderHistory;
import com.myfoods.utility.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OrderHistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get order details from request
        int userId = Integer.parseInt(request.getParameter("userId"));
        String itemName = request.getParameter("itemName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        // Save order details to database
        try (Connection connection = DBConnection.Connection()) {
            String sql = "INSERT INTO order_history (user_id, item_name, quantity, price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);
                statement.setString(2, itemName);
                statement.setInt(3, quantity);
                statement.setDouble(4, price);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Redirect to order history page
        response.sendRedirect("orderHistory.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user ID from request
        int userId = Integer.parseInt(request.getParameter("userId"));

        // Retrieve order history from database
        List<OrderHistory> orderHistory = new ArrayList<>();
        try (Connection connection = DBConnection.Connection()) {
            String sql = "SELECT * FROM order_history WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        OrderHistory order = new OrderHistory();
                        order.setOrderId(resultSet.getInt("order_id"));
                        order.setUserId(resultSet.getInt("user_id"));
                        order.setItemName(resultSet.getString("item_name"));
                        order.setQuantity(resultSet.getInt("quantity"));
                        order.setPrice(resultSet.getDouble("price"));
                        order.setOrderDate(resultSet.getString("order_date"));
                        orderHistory.add(order);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set order history as request attribute and forward to JSP
        request.setAttribute("orderHistory", orderHistory);
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderHistory.jsp");
        dispatcher.forward(request, response);
    }
}
