package com.myfoods.model;

import java.sql.Time;

public class Restaurant {
	private int restaurantId;
	private String name;
	private String address;
	private String phone;
	private Float rating;
	private String cuisineType;
	private boolean isActive;
	private Time eta;
	private String adminUserId;
	private String imagePath;
	
	public Restaurant() {
	
	}

	public Restaurant(int restaurantId, String name, String address, String phone, Float rating, String cuisineType,
			boolean isActive, Time eta, String adminUserId, String imagePath) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.rating = rating;
		this.cuisineType = cuisineType;
		this.isActive = isActive;
		this.eta = eta;
		this.adminUserId = adminUserId;
		this.imagePath = imagePath;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCusineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Time getEta() {
		return eta;
	}

	public void setEta(Time eta) {
		this.eta = eta;
	}

	public String getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
}
