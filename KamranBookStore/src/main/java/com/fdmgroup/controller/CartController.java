package com.fdmgroup.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

@Controller
public class CartController {
	
	@RequestMapping("/viewCart")
	public String goToViewCart(Model model, Principal principal){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		UserDAO userDao = new UserDaoImpl(factory);
		User user = userDao.getUser(principal.getName());
		
		Cart cart = user.getCart();
		
		model.addAttribute("cart", cart);
		return "ViewCart";
	}
	
	@RequestMapping("/updateQuantity")
	public String doUpdateCartItem(@RequestParam int cartItemId, @RequestParam int quantity, Model model, Principal principal){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		UserDAO userDao = new UserDaoImpl(factory);
		User user = userDao.getUser(principal.getName());
		CartItemDAO cartItemDao = new CartItemDaoImpl(factory);
		
		CartItem cartItem = cartItemDao.getCartItem(cartItemId);
		cartItem.setQuantity(quantity);
		
		cartItemDao.updateCartItem(cartItem);
		
		model.addAttribute("cart", user.getCart());
		
		return "ViewCart";
	}
	
}
