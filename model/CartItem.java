package com.myfoods.model;

public class CartItem {
    private int menuId;
    private String name;
    private double price;
    private int quantity;
    private int restaurantId; // Renamed
    
    private String imagePath;

    public CartItem() {
    }

    public CartItem(int id, String name, double price, int quantity, int restaurantId, String imagePath) {
        super();
        this.menuId = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.restaurantId = restaurantId; // Renamed 
        this.imagePath = imagePath;
    }

    public int getMenuId() { // Added
        return menuId;
    }

    public void setMenuId(int menuId) { // Added
        this.menuId = menuId;
    }

    public int getRestaurantId() { // Modified
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) { // Modified
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return menuId;
    }

    public void setId(int id) {
        this.menuId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "[ id=" + menuId + ", name='" + name + "', price=" + price + ", quantity=" + quantity + " ]";
    }
}
