package com.fdmgroup.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.daos.CartDAO;
import com.fdmgroup.daos.CartItemDAO;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.entities.User;
import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.CartItem;

@Controller
public class CartController {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private CartDAO cartDao;
	
	@Autowired
	private CartItemDAO cartItemDao;
	
	@RequestMapping("/viewCart")
	public String goToViewCart(Model model, Principal principal){
		User user = userDao.getUser(principal.getName());
		
		Cart cart = user.getCart();
		
		model.addAttribute("cart", cart);
		return "ViewCart";
	}
	
	@RequestMapping("/updateQuantity")
	public String doUpdateCartItem(@RequestParam int cartItemId, @RequestParam int quantity, Model model, Principal principal){
		
		CartItem cartItem = cartItemDao.getCartItem(cartItemId);
		Cart cart = cartItem.getCart();

		if (quantity == 0){
			cartItemDao.removeCartItem(cartItemId);
		}else{
			
			cartItem.setQuantity(quantity);
			
			cartItemDao.updateCartItem(cartItem);
			
			Cart updatedCart = cartDao.updateCart(cart);
//			cart.calculateTotal();
//			model.addAttribute("cart", updatedCart);
		}
		model.addAttribute("cart",cartDao.getCart(cart.getCartId()));
//		User user = userDao.getUser(principal.getName());
		return "ViewCart";
	}
	
}
