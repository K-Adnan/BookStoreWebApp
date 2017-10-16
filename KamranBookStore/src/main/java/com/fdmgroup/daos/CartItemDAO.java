package com.fdmgroup.daos;

import java.util.List;

import com.fdmgroup.exceptions.EntryAlreadyExistsException;
import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.CartItem;

public interface CartItemDAO {
	
	public void addCartItem(CartItem cartItem);
	
	public CartItem getCartItem(int id);
	
	public void updateCartItem(CartItem cartItem);
	
	public void removeCartItem(int cartItemId);
	
	public List<CartItem> getAllCartItemsForCart(Cart cart);


}
