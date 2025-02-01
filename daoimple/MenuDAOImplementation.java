package com.myfoods.daoimple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myfoods.dao.MenuDAO;
import com.myfoods.model.Menu;
import com.myfoods.utility.DBConnection;

public class MenuDAOImplementation implements MenuDAO {
	
	private static final String ADD_MENU_QUERY="INSERT INTO `menu` (`itemName`,`description`,`price`,`ratings`,`isAvailable`,`imagePath`) values (?,?,?,?,?,?)";
	private static final String GET_MENU_QUERY="SELECT * from `menu` where `menuId`= ?";;
	private static final String UPDATE_MENU_QUERY="UPDATE `menu` SET `itemName`= ?,`description`= ?,`price` =?,`ratings` =?,`isAvailable` =?,`imagePath` =? where `menuId`= ?";
	private static final String DELETE_MENU_QUERY="DELETE from `menu` where `menuId`= ?";
	//private static final String GET_ALL_MENU_QUERY="SELECT * from `menu";
	private static final String GET_ALL_MENU_BY_RESTAURANT_QUERY ="SELECT * from `menu` where `restaurantId` = ?";

	@Override
	public void addMenu(Menu menu) {
		try(Connection connection = DBConnection.Connection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_MENU_QUERY);) 
		{
			preparedStatement.setString(1, menu.getItemName());
			preparedStatement.setString(2, menu.getDescription());
			preparedStatement.setDouble(3, menu.getPrice());
			preparedStatement.setFloat(4, menu.getRatings());
			preparedStatement.setBoolean(5, menu.isAvailable());
			preparedStatement.setString(6, menu.getImagePath());
			
			int res = preparedStatement.executeUpdate();
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Menu getMenu(int menuId) {
		Menu menu=null;
		try(Connection connection = DBConnection.Connection();
			PreparedStatement preparedStatement=connection.prepareStatement(GET_MENU_QUERY);) 
		{
			preparedStatement.setInt(1, menuId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) 
		    { 
				menu=extractMenu(resultSet);
		    }
			else 
			{ 
			   	System.out.println("Restaurant not found!"); 
		    }
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return menu;
	}


	@Override
	public void updateMenu(Menu menu) {
		try(Connection connection = DBConnection.Connection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MENU_QUERY);) 
		{
			preparedStatement.setString(1, menu.getItemName());
			preparedStatement.setString(2, menu.getDescription());
			preparedStatement.setDouble(3, menu.getPrice());
			preparedStatement.setFloat(4, menu.getRatings());
			preparedStatement.setBoolean(5, menu.isAvailable());
			preparedStatement.setString(6, menu.getImagePath());
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMenu(int menuId) {
		try(Connection connection = DBConnection.Connection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MENU_QUERY);) 
		{
			preparedStatement.setInt(1, menuId);
			preparedStatement.executeUpdate();
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private Menu extractMenu(ResultSet resultSet) throws SQLException {
		int menuId=resultSet.getInt("menuId");
		int restaurantId = resultSet.getInt("restaurantId");
		String itemName = resultSet.getString("itemName");
		String description = resultSet.getString("description");
		double price = resultSet.getInt("price");
		float ratings = resultSet.getFloat("ratings");
		boolean isAvailable = resultSet.getBoolean("isAvailable");
		String imagePath = resultSet.getString("imagePath");
		
		Menu menu = new Menu(menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath);
		
		return menu;
	}

	@Override 
	public List<Menu> getAllMenusByRestaurant(int restaurantId) {
		List<Menu> menuList = new ArrayList<>(); 
		try (Connection connection = DBConnection.Connection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MENU_BY_RESTAURANT_QUERY)) 
		{ 
			preparedStatement.setInt(1, restaurantId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) 
			{ 
				Menu menu = extractMenu(resultSet); 
				menuList.add(menu); 
			} 
		} 
		
		catch (SQLException e) 
		{ 
			e.printStackTrace(); 
		} 
		return menuList; 
	}
}
