package com.myfoods.daoimple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.myfoods.dao.RestaurantDAO;
import com.myfoods.model.Restaurant;
import com.myfoods.utility.DBConnection;

public class RestaurantDAOImplementation implements RestaurantDAO {
	
	private static final String ADD_RESTAURANT_QUERY = "INSERT INTO `restaurant` (`name`, `address`, `phone`, `rating`, `cusineType`, `isActive`, `eta`, `imagePath`) VALUES (?,?,?,?,?,?,?,?)"; 
	private static final String GET_RESTAURANT_QUERY = "SELECT * FROM `restaurant` WHERE `restaurantId`= ?"; 
	private static final String UPDATE_RESTAURANT_QUERY = "UPDATE `restaurant` SET `name`=?, `address`=?,`phone`=?,`rating`=?,`cusineType`=?,`isActive`=?,`eta`=?,`imagePath`=? WHERE `restaurantId` = ?"; 
	private static final String DELETE_RESTAURANT_QUERY = "DELETE FROM `restaurant` WHERE `restaurantId`= ?"; 
	private static final String GET_ALL_RESTAURANTS_QUERY = "SELECT * FROM `restaurant`"; 
	private ResultSet resultSet;
	
	@Override
	public void addRestaurant(Restaurant restaurant) {
		try(Connection connection=DBConnection.Connection();
				PreparedStatement preparedStatement=connection.prepareStatement(ADD_RESTAURANT_QUERY);)
		{
			preparedStatement.setString(1, restaurant.getName());
			preparedStatement.setString(2, restaurant.getAddress());
			preparedStatement.setString(3, restaurant.getPhone());
			preparedStatement.setFloat(4, restaurant.getRating());
			preparedStatement.setString(5, restaurant.getCuisineType());
			preparedStatement.setBoolean(6, restaurant.isActive());
			preparedStatement.setTime(7, restaurant.getEta());
			preparedStatement.setString(8, restaurant.getImagePath() );
			
			int res = preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		Restaurant restaurant=null;
		try(Connection connection = DBConnection.Connection();
				 PreparedStatement	preparedStatement = connection.prepareStatement(GET_RESTAURANT_QUERY);) 
		{
			
			preparedStatement.setInt(1, restaurantId);
		    resultSet = preparedStatement.executeQuery();
		 
		    if (resultSet.next()) 
		    { 
		    	restaurant = extractRestaurant(resultSet); 
		    } 
		    else 
		    { 
		    	System.out.println("Restaurant not found!"); 
		    }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurant;
		
	}


	@Override
	public void updateRestaurant(Restaurant restaurant) {
	    try (Connection connection = DBConnection.Connection();
	         PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT_QUERY)) {

	        preparedStatement.setString(1, restaurant.getName());
	        preparedStatement.setString(2, restaurant.getAddress());
	        preparedStatement.setString(3, restaurant.getPhone());
	        preparedStatement.setFloat(4, restaurant.getRating());
	        preparedStatement.setString(5, restaurant.getCuisineType());
	        preparedStatement.setBoolean(6, restaurant.isActive());
	        preparedStatement.setTime(7, restaurant.getEta());
	        preparedStatement.setString(8, restaurant.getImagePath());
	        preparedStatement.setInt(9, restaurant.getRestaurantId()); // Add missing ID

	        // Use executeUpdate() for update queries
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Restaurant updated successfully.");
	        } else {
	            System.out.println("No rows affected. Check if the restaurant ID exists.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Error while updating the restaurant.");
	        e.printStackTrace();
	    }
	}

	@Override
	public void deleteRestaurant(int restaurantId) {

		try(Connection connection = DBConnection.Connection();
				 PreparedStatement	preparedStatement = connection.prepareStatement(DELETE_RESTAURANT_QUERY);) 
		{
			preparedStatement.setInt(1, restaurantId);
			preparedStatement.executeUpdate();
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
	    List<Restaurant> restaurantList = new ArrayList<>();

	    try (Connection connection = DBConnection.Connection()) {
	        if (connection == null) {
	            System.err.println("Failed to establish a database connection.");
	            return restaurantList; // Return empty list
	        }

	        try (Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(GET_ALL_RESTAURANTS_QUERY)) {

	            while (resultSet.next()) {
	                Restaurant restaurant = extractRestaurant(resultSet);
	                restaurantList.add(restaurant);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error retrieving restaurants from database.");
	        e.printStackTrace();
	    }

	    return restaurantList;
	}
	
//	public List<Restaurant> getAllRestaurants() {
//	    List<Restaurant> restaurants = new ArrayList<>();
//	    try (Connection conn = DBConnection.getConnection();
//	         Statement stmt = conn.createStatement();
//	         ResultSet rs = stmt.executeQuery("SELECT * FROM Restaurants")) {
//
//	        while (rs.next()) {
//	            Restaurant r = new Restaurant();
//	            r.setRestaurantId(rs.getInt("id"));
//	            r.setName(rs.getString("name"));
//	            r.setImagePath(rs.getString("image_path"));
//	            restaurants.add(r);
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return restaurants; // Return empty list instead of null
//	}



	public Restaurant extractRestaurant(ResultSet resultSet) throws SQLException 
	{
		int restaurantId = resultSet.getInt("restaurantId");
		String name = resultSet.getString("name");
		String address = resultSet.getString("address");
		String phone = resultSet.getString("phone");
		Float rating = resultSet.getFloat("rating");
		String cusineType = resultSet.getString("cuisineType");
		boolean isActive = resultSet.getBoolean("isActive");
		Time eta = resultSet.getTime("eta");
		String imagePath = resultSet.getString("imagePath");
		
	    Restaurant restaurant = new Restaurant(restaurantId, name, address, phone, rating, cusineType, isActive, eta, cusineType, imagePath);
	    
	    return restaurant;		
	}
}
