package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.shoppingcart.Cart;
import ka.bookstorewebapp.shoppingcart.CartItem;

import java.util.List;

public interface CartItemDAO {

    public void addCartItem(CartItem cartItem);

    public CartItem getCartItem(int id);

    public void updateCartItem(CartItem cartItem);

    public void removeCartItem(int cartItemId);

    public List<CartItem> getAllCartItemsForCart(Cart cart);


}
