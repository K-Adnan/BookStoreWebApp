package com.fdmgroup.daos;

import com.fdmgroup.entities.User;
import com.fdmgroup.shoppingcart.Order;

import java.util.List;

public interface OrderDAO {

    public void addOrder(Order order);

    public void updateOrder(Order order);

    public Order getOrder(int orderId);

    public void removeOrder(int orderId);

    public List<Order> getAllOrdersForUser(User user);

}
