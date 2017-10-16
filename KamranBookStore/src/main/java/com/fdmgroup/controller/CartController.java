package com.fdmgroup.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.daos.CartDAO;
import com.fdmgroup.daos.CartItemDAO;
import com.fdmgroup.daos.OrderDAO;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.entities.User;
import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.CartItem;
import com.fdmgroup.shoppingcart.Order;

@Controller
public class CartController {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private CartDAO cartDao;
	
	@Autowired
	private CartItemDAO cartItemDao;
	
	@Autowired
	private OrderDAO orderDao;
	
	@RequestMapping("/viewCart")
	public String goToViewCart(Model model, Principal principal){
		User user = userDao.getUser(principal.getName());
		
		Cart cart = user.getCart();
		List<CartItem> listOfCartItems = new ArrayList<CartItem>();
		
		for (CartItem eachCartItem : cart.getCartItems()){
			listOfCartItems.add(eachCartItem);
		}
		
		model.addAttribute("cart", cart);
		model.addAttribute("cartItems", listOfCartItems);
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
		}
		model.addAttribute("cart",cartDao.getCart(cart.getCartId()));
		return "ViewCart";
	}
	
	@RequestMapping("/proceedCheckout")
	public String goToCheckout(@RequestParam int cartId, Model model, Principal principal){
		
		Cart cart = cartDao.getCart(cartId);
		User user = userDao.getUser(principal.getName());
		double total = cart.getTotal();
		
		model.addAttribute("total", total);
		model.addAttribute("cart", cart);
		model.addAttribute("user", user);
		return "Checkout";
	}
	
	@RequestMapping("/placeOrder")
	public String doPlaceOrder(@RequestParam int cartId, Model model, Principal principal){
		
		Cart cart = cartDao.getCart(cartId);
		
		Order order = new Order(cart);
		order.setStatus("Order Placed");
		
		orderDao.addOrder(order);
		cartDao.unassignCart(cartId);
		
		return "OrderConfirmation";
	}
	
}
