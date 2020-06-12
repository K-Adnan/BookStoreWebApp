package com.fdmgroup.daos;

import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.CartItem;

import java.util.List;

public interface CartItemDAO {

    public void addCartItem(CartItem cartItem);

    public CartItem getCartItem(int id);

    public void updateCartItem(CartItem cartItem);

    public void removeCartItem(int cartItemId);

    public List<CartItem> getAllCartItemsForCart(Cart cart);


}
