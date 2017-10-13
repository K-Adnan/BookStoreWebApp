package com.fdmgroup.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.daos.BookDAO;
import com.fdmgroup.daos.BookDaoImpl;
import com.fdmgroup.daos.CartDAO;
import com.fdmgroup.daos.CartDaoImpl;
import com.fdmgroup.daos.CartItemDAO;
import com.fdmgroup.daos.CartItemDaoImpl;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.daos.UserDaoImpl;
import com.fdmgroup.entities.Book;
import com.fdmgroup.entities.User;
import com.fdmgroup.exceptions.EntryAlreadyExistsException;
import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.CartItem;

public class SoloProjectRunner {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		UserDAO userDao = new UserDaoImpl(factory);
		BookDAO bookDao = new BookDaoImpl(factory);
		CartItemDAO cartItemDao = new CartItemDaoImpl(factory);
		CartDAO cartDao = new CartDaoImpl(factory);
		
		Book book = bookDao.getBook(1742208863l);
		User user = userDao.getUser("a");
		
		Cart cart = null;
		if (user.getCart() == null){
			cart = new Cart();
			cartDao.addCart(cart);
			user.setCart(cart);
		}else{
			cart = user.getCart();
		}

		CartItem cartItem = new CartItem(book, 1, cart);
		cartItemDao.addCartItem(cartItem);
//		
		cart.addCartItem(cartItem);
//		
//		userDao.updateUser(user);
//		
//		System.out.println(user);
		factory.close();
	}
}
