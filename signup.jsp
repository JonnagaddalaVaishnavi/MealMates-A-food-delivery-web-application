<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up | FoodHub</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <main class="container">
        <div class="form-container">
            <h1 class="text-center">Create your account</h1>
            
            <% if(request.getAttribute("error") != null) { %>
                <div class="error-message">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>
            
            <form action="${pageContext.request.contextPath}/process-signup" method="post">
                <div class="form-group">
                    <label class="form-label">Full Name</label>
                    <input type="text" name="name" class="form-input" required>
                </div>
                <div class="form-group">
                    <label class="form-label">Username</label>
                    <input type="text" name="username" class="form-input" required>
                </div>
                <div class="form-group">
                    <label class="form-label">Email address</label>
                    <input type="email" name="email" class="form-input" required>
                </div>
                <div class="form-group">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" class="form-input" required>
                </div>
                <div class="form-group">
                    <label class="form-label">Phone Number</label>
                    <input type="tel" name="phone" class="form-input" required>
                </div>
                <div class="form-group">
                    <label class="form-label">Address</label>
                    <textarea name="address" class="form-input" required></textarea>
                </div>
                <button type="submit" class="button">Sign up</button>
            </form>

            <div class="text-center" style="margin-top: 1rem;">
                <p>Already have an account?</p>
                <a href="${pageContext.request.contextPath}/signin" class="button button-outline" style="margin-top: 1rem;">Sign in instead</a>
            </div>
        </div>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>