<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myfoods.model.Order" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation - FoodHub</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    
    <main class="confirmation-section">
        <div class="confirmation-container">
            <div class="confirmation-header">
                <h1>Order Confirmed!</h1>
                <p>Thank you for your order.</p>
            </div>
            
            <% 
            Order order = (Order) request.getAttribute("order");
            if (order != null) {
            %>
                <div class="order-details">
                    <h2>Order Details</h2>
                    <p><strong>Order ID:</strong> <%= order.getOrderId() %></p>
                    <p><strong>Total Amount:</strong> ₹<%= String.format("%.2f", order.getTotalAmount()) %></p>
                    <p><strong>Status:</strong> <%= order.getStatus() %></p>
                    <p><strong>Estimated Delivery Time:</strong> 30-45 minutes</p>
                </div>
                
                <div class="delivery-info">
                    <h2>Delivery Information</h2>
                    <p><strong>Name:</strong> <%= order.getUserName() %></p>
                    <p><strong>Phone:</strong> <%= order.getPhone() %></p>
                    <p><strong>Address:</strong> <%= order.getAddress() %></p>
                </div>
            <% } else { %>
                <p>Sorry, we couldn't retrieve your order details. Please contact customer support.</p>
            <% } %>
            
            <div class="confirmation-actions">
                <a href="restaurants" class="button button-primary">Continue Shopping</a>
                <a href="orders" class="button button-secondary">View All Orders</a>
            </div>
        </div>
    </main>
    
    <%@ include file="footer.jsp" %>
</body>
</html>

