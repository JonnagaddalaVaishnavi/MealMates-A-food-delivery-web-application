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
import jakarta.servlet.http.HttpSession;

public class SigninServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodhub", "root", "password");
            
            PreparedStatement pstmt = conn.prepareStatement(
                "SELECT * FROM users WHERE email = ? AND password = ?"
            );
            pstmt.setString(1, email);
            pstmt.setString(2, password); // In production, use password hashing
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Update last login date
                String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                PreparedStatement updateStmt = conn.prepareStatement(
                    "UPDATE users SET lastLoginDate = ? WHERE email = ?"
                );
                updateStmt.setString(1, currentTime);
                updateStmt.setString(2, email);
                updateStmt.executeUpdate();
                
                // Create session
                HttpSession session = request.getSession();
                session.setAttribute("userId", rs.getInt("userId"));
                session.setAttribute("name", rs.getString("name"));
                session.setAttribute("role", rs.getString("role"));
                
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                request.setAttribute("error", "Invalid email or password!");
                request.getRequestDispatcher("/signin").forward(request, response);
            }
            
            conn.close();
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("signin.jsp").forward(request, response);
        }
    }
}