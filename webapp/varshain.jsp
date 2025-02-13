<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In - TapFoods</title>
    <link rel="stylesheet" href="New.css">
</head>
<body>
    <main class="auth-container signin-container">
        <div class="auth-card signin-card">
            <h2>Welcome Back</h2>
            <% if (request.getAttribute("error") != null) { %>
                <p class="error-message"><%= request.getAttribute("error") %></p>
            <% } %>
            <% if (request.getAttribute("message") != null) { %>
                <p class="success-message"><%= request.getAttribute("message") %></p>
            <% } %>
            <form class="auth-form signin-form" action="${pageContext.request.contextPath}/SignInServletNew" method="post">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit" class="auth-btn signin-btn">Sign In</button>
            </form>
            <p class="auth-link">Don't have an account? <a href="${pageContext.request.contextPath}/varshaout.jsp">Sign Up</a></p>
        </div>
    </main>
    <footer class="footer">
        <p>&copy; 2025 TapFoods. All rights reserved.</p>
    </footer>
</body>
</html>

