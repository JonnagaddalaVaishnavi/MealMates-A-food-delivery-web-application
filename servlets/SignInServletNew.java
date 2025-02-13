package com.myfoods.servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import com.myfoods.daoimple.UserDAOImplementation;
import com.myfoods.model.User;
import com.myfoods.model.OrderHistory;
import com.myfoods.utility.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SignInServletNew")
public class SignInServletNew extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAOImplementation udi = new UserDAOImplementation();
        User user = udi.getUserByEmail(email);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                LocalDateTime now = LocalDateTime.now();
                user.setLastLoginDate(now);
                udi.updateLastLoginDate(user.getUserId(), now);

                HttpSession session = req.getSession();
                session.setAttribute("userId", user.getUserId());

                // Fetch order history for the user
                List<OrderHistory> orderHistory = new ArrayList<>();
                try (Connection connection = DBConnection.Connection()) {
                    String sql = "SELECT * FROM order_history WHERE user_id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setInt(1, user.getUserId());
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

                // Set order history as session attribute
                session.setAttribute("orderHistory", orderHistory);

                // Redirect to Home page if user already exists
                resp.sendRedirect(req.getContextPath() + "/Home");
            } else {
                req.setAttribute("error", "Invalid password");
                req.getRequestDispatcher("varshain.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "User not found");
            req.getRequestDispatcher("varshain.jsp").forward(req, resp);
        }
    }
}
