<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List,java.util.ArrayList,com.myfoods.model.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodHub - Food Delivery</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <main class="restaurants-section">
	<section class="food-categories">
    <div class="container">
        <div class="categories-header">
            <h2 class="section-title">Order our best food options</h2>
            <div class="navigation-buttons">
                <button class="nav-btn prev" id="prevBtn">
                    <span>←</span>
                </button>
                <button class="nav-btn next" id="nextBtn">
                    <span>→</span>
                </button>
            </div>
        </div>
        
        <div class="categories-wrapper">
            <div class="categories-container">
                <div class="category-card">
                    <img src="images/Biryani.jpg" alt="Biryani">
                    <h3>Biryani</h3>
                </div>
                <div class="category-card">
                    <img src="images/north-indian-cuisine.jpg" alt="North Indian">
                    <h3>North Indian</h3>
                </div>
                <div class="category-card">
                    <img src="images/south-indian.jpg" alt="South Indian">
                    <h3>South Indian</h3>
                </div>
                <div class="category-card">
                    <img src="images/chinese.jpg" alt="Chinese">
                    <h3>Chinese</h3>
                </div>
                <div class="category-card">
                    <img src="images/pizza.jpg" alt="Pizza">
                    <h3>Pizza</h3>
                </div>
                <div class="category-card">
                    <img src="images/burger.jpg" alt="Burgers">
                    <h3>Burgers</h3>
                </div>
                <div class="category-card">
                    <img src="https://res.cloudinary.com/dj5yf27lr/image/upload/v1695820872/food-delivery/desserts_yg9sms.jpg" alt="Desserts">
                    <h3>Desserts</h3>
                </div>
                <div class="category-card">
                    <img src="https://res.cloudinary.com/dj5yf27lr/image/upload/v1695820872/food-delivery/thali_yzmsub.jpg" alt="Thali">
                    <h3>Thali</h3>
                </div>
                <div class="category-card">
                    <img src="https://res.cloudinary.com/dj5yf27lr/image/upload/v1695820872/food-delivery/beverages_kzfqvt.jpg" alt="Beverages">
                    <h3>Beverages</h3>
                </div>
                <div class="category-card">
                    <img src="https://res.cloudinary.com/dj5yf27lr/image/upload/v1695820872/food-delivery/street-food_puxeul.jpg" alt="Street Food">
                    <h3>Street Food</h3>
                </div>
            </div>
        </div>
    </div>
</section>
        <div class="container">
            <h1 class="section-title">Restaurants near you</h1>
            
            <div class="restaurant-grid">
                <%
                    List<Restaurant> restaurants = (List<Restaurant>)request.getAttribute("allrestaurants");
                
                    if (restaurants == null) { 
                        restaurants = new ArrayList<>(); 
                    }
                
                    for(Restaurant r: restaurants) {
                %>
					<a href="selectRestaurant?restaurant_id=<%=r.getRestaurantId()%>" class="restaurant-card">
                    <div class="restaurant-image">
                    
                        <img src="<%= r.getImagePath() %>" alt="<%= r.getName() %>">
                        
                    </div>
                    
                    <div class="restaurant-info">
                        <h2 class="restaurant-name"><%= r.getName() %></h2>
                        <p class="restaurant-cuisine"><%= r.getCuisineType()%></p>
                        <div class="restaurant-meta">
                            <div class="rating-wrapper">
                                <span class="rating-star">⭐</span>
                                <span class="rating-value"><%= r.getRating()%></span>
                            </div>
                            <span class="restaurant-time"><%= r.getEta()%> mins</span>
                            <span class="restaurant-price">₹500 for two</span>
                        </div>
                    </div>
                    
                </a>
                <%
                    }
                %>
            </div>
        </div>
    </main>
    
    <%@ include file="footer.jsp" %>
    <script>
document.addEventListener('DOMContentLoaded', function() {
    const wrapper = document.getElementById('categoriesWrapper');
    const container = document.getElementById('categoriesContainer');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    
    let scrollPosition = 0;
    const cardWidth = 220; // width + gap
    
    function updateScrollButtons() {
        // Enable/disable previous button
        prevBtn.disabled = scrollPosition <= 0;
        
        // Enable/disable next button
        const maxScroll = container.scrollWidth - wrapper.clientWidth;
        nextBtn.disabled = scrollPosition >= maxScroll;
        
        // Update button opacity
        prevBtn.style.opacity = prevBtn.disabled ? '0.5' : '1';
        nextBtn.style.opacity = nextBtn.disabled ? '0.5' : '1';
    }
    
    function scrollCategories(direction) {
        const containerWidth = wrapper.clientWidth;
        const scrollAmount = Math.floor(containerWidth / cardWidth) * cardWidth;
        
        if (direction === 'prev') {
            scrollPosition = Math.max(0, scrollPosition - scrollAmount);
        } else {
            const maxScroll = container.scrollWidth - wrapper.clientWidth;
            scrollPosition = Math.min(maxScroll, scrollPosition + scrollAmount);
        }
        
        container.style.transform = `translateX(-${scrollPosition}px)`;
        updateScrollButtons();
    }
    
    // Add click event listeners
    prevBtn.addEventListener('click', () => scrollCategories('prev'));
    nextBtn.addEventListener('click', () => scrollCategories('next'));
    
    // Update button states on window resize
    window.addEventListener('resize', updateScrollButtons);
    
    // Initial button state
    updateScrollButtons();
});
</script>
</body>
</html>
