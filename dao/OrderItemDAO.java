package com.myfoods.dao;

import java.util.List;

import com.myfoods.model.OrderItem;

public interface OrderItemDAO {
	void addOrderItem(OrderItem orderItem);
	OrderItem getOrderItem(int orderItemId);
	void updateOrderItem(OrderItem orderItem);
	void deleteOrderItem(int orderItemId);
	List<OrderItem> getAllOrderItemsByOrder(int orderId);
}
