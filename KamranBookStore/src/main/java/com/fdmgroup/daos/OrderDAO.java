package com.fdmgroup.daos;

import java.util.List;

import com.fdmgroup.entities.User;
import com.fdmgroup.exceptions.EntryAlreadyExistsException;
import com.fdmgroup.exceptions.NoSuchUserException;
import com.fdmgroup.shoppingcart.Order;

public interface OrderDAO {
	
	public void addOrder(Order order);
	
	public void updateOrder(Order order);
	
	public Order getOrder(int orderId);
	
	public void removeOrder(int orderId);
	
	public List<Order> getAllOrdersForUser(User user);

}
