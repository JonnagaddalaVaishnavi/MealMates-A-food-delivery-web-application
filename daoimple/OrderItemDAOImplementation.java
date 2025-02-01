package com.myfoods.daoimple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myfoods.dao.OrderItemDAO;
import com.myfoods.model.OrderItem;
import com.myfoods.utility.DBConnection;

public class OrderItemDAOImplementation implements OrderItemDAO {

    private static final String ADD_ORDERITEM_QUERY="INSERT INTO `orderitem` (`quantity`, `totalprice`) VALUES (?, ?)";
    private static final String GET_ORDERITEM_QUERY="SELECT * FROM `orderitem` WHERE `orderitemid` = ?";
    private static final String UPDATE_ORDERITEM_QUERY="UPDATE `orderitem` SET `quantity` = ?, `totalprice` = ? WHERE `orderItemId` = ?";
    private static final String DELETE_ORDERITEM_QUERY="DELETE FROM `orderitem` WHERE `orderitemid` = ?";
    private static final String GET_ALL_ORDERITEMS_BY_ORDER_QUERY = "SELECT * FROM `orderitem` WHERE `orderId` = ?";

    @Override
    public void addOrderItem(OrderItem orderItem) {
        try (Connection connection = DBConnection.Connection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDERITEM_QUERY)) {
            preparedStatement.setInt(1, orderItem.getQuantity());
            preparedStatement.setInt(2, orderItem.getTotalPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        OrderItem orderItem = null;
        try (Connection connection = DBConnection.Connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERITEM_QUERY)) {
            preparedStatement.setInt(1, orderItemId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                orderItem = extractOrderItem(resultSet);
            } else {
                System.out.println("Order Item not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try (Connection connection = DBConnection.Connection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDERITEM_QUERY)) {
            preparedStatement.setInt(1, orderItem.getQuantity());
            preparedStatement.setInt(2, orderItem.getTotalPrice());
            preparedStatement.setInt(3, orderItem.getOrderItemId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        try (Connection connection = DBConnection.Connection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDERITEM_QUERY)) {
            preparedStatement.setInt(1, orderItemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderItem> getAllOrderItemsByOrder(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection connection = DBConnection.Connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERITEMS_BY_ORDER_QUERY)) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderItem orderItem = extractOrderItem(resultSet);
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    private OrderItem extractOrderItem(ResultSet resultSet) throws SQLException {
        int orderItemId = resultSet.getInt("orderItemId");
        int orderId = resultSet.getInt("orderId");
        int menuId = resultSet.getInt("menuId");
        int quantity = resultSet.getInt("quantity");
        int totalPrice = resultSet.getInt("totalPrice");

        return new OrderItem(orderItemId, orderId, menuId, quantity, totalPrice);
    }
}
