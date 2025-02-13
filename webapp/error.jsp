<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - FoodHub</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    
    <main class="error-section">
        <div class="error-container">
            <div class="error-header">
                <h1>Oops! Something went wrong</h1>
                <p>We're sorry, but something went wrong with your request.</p>
            </div>
            
            <div class="error-details">
                <p>Please try again later or contact our support team if the issue persists.</p>
            </div>
            
            <div class="error-actions">
                <a href="Home" class="home-btn">Go to Homepage</a>
                <a href="contact.jsp" class="contact-btn">Contact Support</a>
            </div>
        </div>
    </main>
    
    <%@ include file="footer.jsp" %>
</body>
</html>
