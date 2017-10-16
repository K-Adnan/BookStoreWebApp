package com.fdmgroup.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.shoppingcart.Order;

public class OrderDaoImpl implements OrderDAO {

	@Autowired
	private EntityManagerFactory factory;

	public OrderDaoImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}

	public OrderDaoImpl() {
	}

	public void addOrder(Order newOrder) {
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		manager.persist(newOrder);
		manager.getTransaction().commit();
	}

	public void updateOrder(Order order) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(order);
		manager.getTransaction().commit();
	}

	public Order getOrder(int orderId) {
		EntityManager manager = factory.createEntityManager();
		Order order = manager.find(Order.class, orderId);

		return order;
	}

	public void removeOrder(int orderId) {

		EntityManager manager = factory.createEntityManager();

		Order order = manager.find(Order.class, orderId);
		manager.getTransaction().begin();

		manager.remove(order);
		manager.getTransaction().commit();
	}

	public List<Order> getAllOrders() {
		EntityManager manager = factory.createEntityManager();

		TypedQuery<Order> query = manager.createQuery("select u from Order u", Order.class);
		List<Order> listOfAllOrders = query.getResultList();

		return listOfAllOrders;
	}

}
