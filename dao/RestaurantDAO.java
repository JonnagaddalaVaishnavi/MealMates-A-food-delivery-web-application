package com.myfoods.dao;	

import java.util.List;

import com.myfoods.model.Restaurant;

public interface RestaurantDAO {           
	void addRestaurant(Restaurant restaurant);
	Restaurant getRestaurant(int restaurantId);
	void updateRestaurant(Restaurant restaurant);
	void deleteRestaurant(int restaurantId);
	List<Restaurant> getAllRestaurants();
	
}






