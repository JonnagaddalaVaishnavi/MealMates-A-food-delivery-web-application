<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myfoods.model.*" %>
<%@ page import="com.myfoods.utility.Cart" %>
<%@ page import="jakarta.servlet.http.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout - FoodHub</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    
    <main class="checkout-section">
        <div class="checkout-container">
            <div class="checkout-header">
                <h1>Checkout</h1>
                <p>Please enter your delivery details</p>
            </div>
            
            <%
                HttpSession session1 = request.getSession();
                Cart cart = (Cart) session1.getAttribute("cart");
                if (cart != null && !cart.getItems().isEmpty()) {
                    double totalAmount = 0.0;
                    for (CartItem item : cart.getItems().values()) {
                        totalAmount += item.getPrice() * item.getQuantity();
                    }
            %>
            
            <form action="placeorder" method="POST" class="checkout-form">
            
                <input type="hidden" name="totalAmount" value="<%= totalAmount %>">
                
                <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" required class="form-input" 
                           placeholder="Enter your full name">
                </div>
                
                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="tel" id="phone" name="phone" required class="form-input" 
                           pattern="[0-9]{10}" placeholder="Enter your phone number">
                </div>
                
                <div class="form-group">
                    <label for="address">Delivery Address</label>
                    <textarea id="address" name="address" required class="form-input" 
                            rows="3" placeholder="Enter your complete delivery address"></textarea>
                </div>
                
                <div class="form-group">
                    <label for="paymentMode">Payment Mode</label>
                    <select id="paymentMode" name="paymentMode" required class="form-input">
                        <option value="cash">Cash on Delivery</option>
                        <option value="card">Card Payment</option>
                        <option value="upi">UPI Payment</option>
                    </select>
                </div>
                
                <button type="submit" class="place-order-btn">Place Order ₹<%= String.format("%.2f", totalAmount) %></button>
            </form>
            
            <% } else { %>
                <div class="empty-cart-message">
                    <p>Your cart is empty. Please add items to proceed with checkout.</p>
                    <a href="Home" class="back-to-restaurants">Browse Restaurants</a>
                </div>
            <% } %>
        </div>
    </main>
    
    <%@ include file="footer.jsp" %>
</body>
</html>
