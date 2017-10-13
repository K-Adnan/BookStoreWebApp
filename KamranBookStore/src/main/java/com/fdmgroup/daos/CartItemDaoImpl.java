package com.fdmgroup.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.fdmgroup.exceptions.EntryAlreadyExistsException;
import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.CartItem;

public class CartItemDaoImpl implements CartItemDAO {

	private EntityManagerFactory factory;

	public CartItemDaoImpl(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public void addCartItem(CartItem cartItem) {
//		if (getCartItem(cartItem.getIsbn()) == null) {
			EntityManager manager = factory.createEntityManager();
			manager.getTransaction().begin();
			manager.persist(cartItem);
			manager.getTransaction().commit();
//		} else {
//			throw new EntryAlreadyExistsException("CartItem already exists for book with ISBN " + cartItem.getIsbn()
//					+ ". " + "To add another quantity for this book, increase quantity of already existing CartItem");
//		}
	}

	public CartItem getCartItem(long isbn) {
		EntityManager manager = factory.createEntityManager();
		CartItem cartItem = manager.find(CartItem.class, isbn);

		return cartItem;
	}

	public void updateCartItem(CartItem cartItem) {
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		manager.merge(cartItem);
		manager.getTransaction().commit();
	}

	public void removeCartItem(long isbn) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();

		CartItem cartItem = manager.find(CartItem.class, isbn);
		manager.remove(cartItem);
		manager.getTransaction().commit();

	}

	public List<CartItem> getAllCartItemsForCart(Cart cart) {
		EntityManager manager = factory.createEntityManager();
		
		TypedQuery<CartItem> query = manager.createQuery("Select c from CartItem c where c.cartId = ?", CartItem.class);
		query.setParameter(1, cart);
		
		List<CartItem> cartItems = query.getResultList();
		
		return cartItems;
	}

}
