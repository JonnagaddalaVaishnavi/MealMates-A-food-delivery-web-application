<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.*"%>
<%@ page import="java.util.List, java.util.ArrayList"%>
<%@ page import="com.myfoods.model.Menu" %>
<%@ page import="com.myfoods.model.CartItem"%>
<%@ page import="com.myfoods.utility.Cart"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    <main class="container">
        <h1>Your Cart</h1>
        <%
        HttpSession session1 = request.getSession();
        Cart cart = (Cart) session1.getAttribute("cart");
        double totalPrice = 0.0;
        Integer currentRestaurantIdInt = (Integer) session1.getAttribute("restaurant_id");
        String currentRestaurantId = null;
        if (currentRestaurantIdInt != null) {
            currentRestaurantId = currentRestaurantIdInt.toString();
            System.out.println("Current Restaurant ID: " + currentRestaurantId); // Debugging print
        } else {
            // Handle the case where currentRestaurantIdInt is null
            throw new ServletException("Restaurant ID is missing in the session.");
        }


            if (cart != null && !cart.getItems().isEmpty()) {
        %>
            <div class="cart-items">
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>Image</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total Price</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (CartItem item : cart.getItems().values()) {
                                double itemTotal = item.getPrice() * item.getQuantity();
                                totalPrice += itemTotal;
                        %>
                        <tr>
                            <td><img src="<%= item.getImagePath() %>" alt="<%= item.getName() %>" class="cart-item-image"></td>
                            <td><%= item.getName() %></td>
                            <td>
                                <form action="cart" method="POST" class="update-quantity-form">
                                    <input type="hidden" name="menu_id" value="<%= item.getMenuId() %>">
                                    <input type="hidden" name="restaurant_id" value="<%= item.getRestaurantId() %>">
                                    <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="0" class="quantity-input">
                                    <button type="submit" name="action" value="update" class="update-button button-class">Update</button>
                                </form>
                            </td>
                            <td>₹<%= item.getPrice() %></td>
                            <td>₹<%= itemTotal %></td>
                            <td>
                                <form action="cart" method="POST" class="remove-item-form">
                                    <input type="hidden" name="menu_id" value="<%= item.getMenuId() %>">
                                    <input type="hidden" name="restaurant_id" value="<%= item.getRestaurantId() %>">
                                    <button type="submit" name="action" value="remove" class="remove-button button-class">Remove</button>
                                </form>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <div class="cart-summary">
			    <h2>Total Price: ₹<%= totalPrice %></h2>
			    <a href="Checkout.jsp" class="checkout-button button-class">Proceed to Checkout</a>
			    <form action="menu" method="get" class="form-inline">
			        <input type="hidden" name="restaurant_id" value="<%= currentRestaurantId != null ? currentRestaurantId : "" %>">
			        <input type="submit" value="Add More Items" class="add-more-button button-class">
			    </form>
			    <form action="cart" method="post" class="form-inline">
			        <input type="hidden" name="restaurant_id" value="<%= currentRestaurantId != null ? currentRestaurantId : "" %>">
			        <input type="hidden" name="action" value="clear">
			        <button type="submit" class="clear-cart-button button-class">Clear Cart</button>
			    </form>
			</div>
            <!--  <div class="cart-summary">
                <h2>Total Price: ₹</h2>
                <form action="checkout" method="post" class="form-inline">
                    <input type="submit" value="Proceed to Checkout" class="checkout-button button-class">
                </form>
                <form action="menu" method="get" class="form-inline">
                    <input type="hidden" name="restaurant_id" value="<%= currentRestaurantId != null ? currentRestaurantId : "" %>">
                    <input type="submit" value="Add More Items" class="add-more-button button-class">
                </form>
				<form action="cart" method="post" class="form-inline">
				    <input type="hidden" name="restaurant_id" value="<%= currentRestaurantId != null ? currentRestaurantId : "" %>">
				    <input type="hidden" name="action" value="clear">
				    <button type="submit" class="clear-cart-button button-class">Clear Cart</button>
				</form>
            </div> -->
        <%
            } else {
        %>
        <p>Your cart is empty.</p>
        <% } %>
    </main>

    <%@ include file="footer.jsp" %>

</body>
</html>
