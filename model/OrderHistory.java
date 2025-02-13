package com.myfoods.model;

public class OrderHistory {
    private int orderId;
    private int userId;
    private String itemName;
    private int quantity;
    private double price;
    private String orderDate;
    
    public OrderHistory() {

    }
    
    public OrderHistory(int orderId, int userId, String itemName, int quantity, double price, String orderDate) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.orderDate = orderDate;
	}
    
	// Getters and setters
    
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

    
}
