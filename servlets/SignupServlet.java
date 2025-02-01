package com.myfoods.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String role = "customer"; // Default role for signup
        
     // In the doPost method, update the redirects:
        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/signin");
        } else {
            request.setAttribute("error", "Registration failed!");
            request.getRequestDispatcher("/signup").forward(request, response);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodhub", "root", "password");
            
            // Check if email already exists
            PreparedStatement checkStmt = conn.prepareStatement("SELECT email FROM users WHERE email = ?");
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                request.setAttribute("error", "Email already exists!");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                return;
            }

            // Insert new user
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO users (name, username, password, email, phone, address, role, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            
            pstmt.setString(1, name);
            pstmt.setString(2, username);
            pstmt.setString(3, password); // In production, use password hashing
            pstmt.setString(4, email);
            pstmt.setString(5, phone);
            pstmt.setString(6, address);
            pstmt.setString(7, role);
            pstmt.setString(8, currentTime);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                response.sendRedirect("signin.jsp");
            } else {
                request.setAttribute("error", "Registration failed!");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
            
            conn.close();
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}