package com.myfoods.daoimple;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.myfoods.dao.OrderDAO;
import com.myfoods.model.Order;
import com.myfoods.utility.DBConnection;
public class OrderDAOImplementation implements OrderDAO {
	private static final String ADD_ORDER_QUERY="INSERT INTO `order` (`orderDate`,`totalAmount`,`status`,`paymentMode`) values (?,?,?,?)";
	private static final String GET_ORDER_QUERY="SELECT * from `order` where `orderId`= ? ";
	private static final String UPDATE_ORDER_QUERY="UPDATE `order` set `orderDate`=? ,`totalAmount`= ?,`status` =? ,`paymentMode` =? where `orderId`= ? ";
	private static final String DELETE_ORDER_QUERY="delete from `order` where `orderId` = ?";
	//private static final String GET_ALL_ORDERS_QUERY="select * from `order`";
	private static final String GET_ORDERS_BY_USER_QUERY = "select * from `order`where `userId` = ?";

	@Override
	public void addOrder(Order order) {
		
		try(Connection connection = DBConnection.Connection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER_QUERY);) 
		{
			preparedStatement.setDate(1, (Date) order.getOrderDate());
			preparedStatement.setInt(2, order.getTotalAmount());
			preparedStatement.setString(3, order.getStatus());
			preparedStatement.setString(4, order.getPaymentMode());
			
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Order getOrder(int OrderId) {
		
		Order order=null;
		try(Connection connection = DBConnection.Connection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_QUERY);) 
		{
			preparedStatement.setInt(1, OrderId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) 
		    { 
				order = extractOrder(resultSet);
		    }
			else 
			{ 
			   	System.out.println("Order not found!"); 
		    }
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return order;
	}


	@Override
	public void updateOrder(Order order) {
		try(Connection connection = DBConnection.Connection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_QUERY);) 
		{
			preparedStatement.setDate(1, (Date) order.getOrderDate());
			preparedStatement.setInt(2, order.getTotalAmount());
			preparedStatement.setString(3, order.getStatus());
			preparedStatement.setString(4, order.getPaymentMode());
			
			preparedStatement.executeUpdate();
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void deleteOrder(int OrderId) {
		try(Connection connection = DBConnection.Connection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_QUERY);) 
		{
			preparedStatement.setInt(1, OrderId);
			preparedStatement.executeUpdate();
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	public List<Order> getAllOrdersByUser(int userId) {
		List<Order> orders = new ArrayList<>(); 
		try (Connection connection = DBConnection.Connection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS_BY_USER_QUERY);) 
		{ 
			preparedStatement.setInt(1, userId); 
			ResultSet resultSet = preparedStatement.executeQuery(); 
			while (resultSet.next()) 
			{ 
				Order order = extractOrder(resultSet); 
				orders.add(order); 
			} 
		} 
		
		catch (SQLException e) 
		{ 
			e.printStackTrace(); 
		} 
		return orders; 
	}
	
	private Order extractOrder(ResultSet resultSet) throws SQLException {
		
		 int orderId = resultSet.getInt("orderId");
		 int userId = resultSet.getInt("userId");
		 int restaurantId = resultSet.getInt("restaurantId");
		 Date orderDate = resultSet.getDate("orderDate");
		 int totalAmount = resultSet.getInt("totalAmount");
		 String status =  resultSet.getString("status");
		 String paymentMode = resultSet.getString("paymentMode");
		 
		  Order order = new Order(orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMode);
		
		  return order;
	}

}
