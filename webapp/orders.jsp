<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation - FoodHub</title>
    <link rel="stylesheet" href="styles.css">
    <%@ include file="header.jsp" %>
    <style>
    h1 {
    color:#f8f8f8;
    }
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        .confirmation-section {
            max-width: 800px;
            margin: 50px auto;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }
        .confirmation-header {
            background-color:#fc8019;
            
            padding: 20px;
            text-align: center;
        }
        .confirmation-header h1 {
            margin: 0;
            font-size: 28px;
            color:#f8f8f8;
        }
        .confirmation-body {
            padding: 20px;
        }
        .confirmation-body h2 {
            margin-top: 0;
            font-size: 24px;
            border-bottom: 2px solid #ddd;
            padding-bottom: 10px;
        }
        .confirmation-details {
            margin-bottom: 20px;
        }
        .confirmation-details p {
            margin: 8px 0;
        }
        .order-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .order-table th, .order-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .order-table th {
            background-color: #f2f2f2;
        }
        .total-row {
            font-weight: bold;
        }
        .thank-you {
            text-align: center;
            font-size: 18px;
            color: #555;
        }
        .back-to-home {
            display: inline-block;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #fc8019;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        .back-to-home:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="confirmation-section">
        <div class="confirmation-header">
            <h1>Order Confirmation</h1>
        </div>
        <div class="confirmation-body">
            <h2>Thank you, <%= request.getAttribute("customerName") %>!</h2>
            <div class="confirmation-details">
                <p><strong>Delivery Address:</strong> <%= request.getAttribute("address") %></p>
                <p><strong>Phone Number:</strong> <%= request.getAttribute("phone") %></p>
                <p><strong>Payment Mode:</strong> <%= request.getAttribute("paymentMode") %></p>
            </div>

            <h2>Order Details</h2>
            <table class="order-table">
                <tr>
                    <th>Item</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
                <%
                    Map<String, Map<String, String>> orderDetails = (Map<String, Map<String, String>>) request.getAttribute("orderDetails");
                    double totalAmount = 0.0;

                    if (orderDetails != null) {
                        for (Map.Entry<String, Map<String, String>> entry : orderDetails.entrySet()) {
                            String itemName = entry.getKey();
                            Map<String, String> itemDetails = entry.getValue();
                            String itemQuantity = itemDetails.get("quantity");
                            String itemPrice = itemDetails.get("price");

                            double itemTotal = Double.parseDouble(itemPrice) * Integer.parseInt(itemQuantity);
                            totalAmount += itemTotal;
                %>
                <tr>
                    <td><%= itemName %></td>
                    <td><%= itemQuantity %></td>
                    <td>₹<%= String.format("%.2f", itemTotal) %></td>
                </tr>
                <%
                        }
                    }
                %>
                <tr class="total-row">
                    <td colspan="2">Total</td>
                    <td>₹<%= String.format("%.2f", totalAmount) %></td>
                </tr>
            </table>

            <div class="thank-you">
                <p>Your order has been successfully placed and will be delivered soon!</p>
            </div>
            <div style="text-align: center;">
                <a href="Home" class="back-to-home">Back to Home</a>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
