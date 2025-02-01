package com.myfoods.dao;

import java.util.List;

import com.myfoods.model.Order;

public interface OrderDAO {
	void addOrder(Order order);
	Order getOrder(int OrderId);
	void updateOrder(Order order);
	void deleteOrder(int OrderId);
	List<Order> getAllOrdersByUser(int userId);
	
}
