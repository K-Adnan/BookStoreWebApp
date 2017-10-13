package com.fdmgroup.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.fdmgroup.shoppingcart.Cart;

public class CartDaoImpl implements CartDAO {

	private EntityManagerFactory factory;

	public CartDaoImpl(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public void addCart(Cart cart) {
			EntityManager manager = factory.createEntityManager();
			manager.getTransaction().begin();
			manager.persist(cart);
			manager.getTransaction().commit();
	}

	public Cart getCart(long isbn) {
		EntityManager manager = factory.createEntityManager();
		Cart cart = manager.find(Cart.class, isbn);

		return cart;
	}

	public void updateCart(Cart cart) {
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		manager.merge(cart);
		manager.getTransaction().commit();
	}

	public void removeCart(long isbn) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();

		Cart cart = manager.find(Cart.class, isbn);
		manager.remove(cart);
		manager.getTransaction().commit();

	}

	public List<Cart> getAllCartsForCart(Cart cart) {
		EntityManager manager = factory.createEntityManager();
		
		TypedQuery<Cart> query = manager.createQuery("Select c from Cart c where c.cartId = ?", Cart.class);
		query.setParameter(1, cart);
		
		List<Cart> carts = query.getResultList();
		
		return carts;
	}

}
