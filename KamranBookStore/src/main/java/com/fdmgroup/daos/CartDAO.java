package com.fdmgroup.daos;

import java.util.List;

import com.fdmgroup.shoppingcart.Cart;

public interface CartDAO {
	
	public void addCart(Cart cart);
	
	public Cart getCart(int cartId);
	
	public Cart updateCart(Cart cart);
	
	public void removeCart(int cartId);
	
	public List<Cart> getAllCartsForCart(Cart cart);
	
	public void unassignCart(int cartId);


}
