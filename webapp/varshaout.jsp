<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up - TapFoods</title>
    <link rel="stylesheet" href="New.css">
</head>
<body>
    <main class="auth-container signup-container">
        <div class="auth-card signup-card">
            <h2>Create Account</h2>
            <% if (request.getAttribute("error") != null) { %>
                <p class="error-message"><%= request.getAttribute("error") %></p>
            <% } %>
            <form class="auth-form signup-form" action="${pageContext.request.contextPath}/signUpServletNew" method="POST">
            
                <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="tel" id="phone" name="phone" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <textarea id="address" name="address" required></textarea>
                </div>
                <button type="submit" class="auth-btn signup-btn">Sign Up</button>
            </form>
            <p class="auth-link">Already have an account? <a href="${pageContext.request.contextPath}/varshain.jsp">Sign In</a></p>
        </div>
    </main>
    <footer class="footer">
        <p>&copy; 2025 TapFoods. All rights reserved.</p>
    </footer>
</body>
</html>

