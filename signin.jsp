<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In | FoodHub</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <main class="container">
        <div class="form-container">
            <h1 class="text-center">Sign in to your account</h1>
            
            <% if(request.getAttribute("error") != null) { %>
                <div class="error-message">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>
            
            <form action="${pageContext.request.contextPath}/process-signin" method="post">
                <div class="form-group">
                    <label class="form-label">Email address</label>
                    <input type="email" name="email" class="form-input" required>
                </div>
                <div class="form-group">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" class="form-input" required>
                </div>
                <button type="submit" class="button">Sign in</button>
            </form>

            <div class="text-center" style="margin-top: 1rem;">
                <p>New to FoodHub?</p>
                <a href="${pageContext.request.contextPath}/signup" class="button button-outline" style="margin-top: 1rem;">Create an account</a>
            </div>
        </div>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>