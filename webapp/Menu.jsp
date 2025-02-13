<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.myfoods.model.Menu"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <main class="container">
        <div class="restaurant-info">
            <div class="restaurant-header">
                <div>
                    <h1>Menu</h1>
                </div>
            </div>
        </div>
            <% 
            List<Menu> menulist = (List<Menu>)request.getAttribute("menus");
            for(Menu m : menulist) {
            %>

     <div class="menu-item">
	    <img src="<%=m.getImagePath()%>" alt="<%=m.getItemName()%>" class="menu-image">
	    <div class="menu-info">
	        <h3 class="menu-name"><%=m.getItemName()%></h3>
	        <p class="menu-price">₹<%= (int)m.getPrice()%></p>
	        <p class="menu-description"><%=m.getDescription()%></p>
	    </div>
    
	    <div class="button-actions">
	        <form action="cart" method="POST" class="add-to-cart-form">
	            <input type="hidden" name="menu_id" value="<%=m.getMenuId()%>">
	            <input type="hidden" name="restaurant_id" value="<%=m.getRestaurantId()%>">
	            <input type="number" name="quantity" value="1" min="1" class="quantity-input">
	            <input type="hidden" name="action" value="add">
	            <button type="submit" class="add-to-cart">Add to Cart</button>
	        </form>
	    </div>
	</div>
            <% } %>
        </div>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>