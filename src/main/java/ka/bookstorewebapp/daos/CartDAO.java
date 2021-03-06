package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.shoppingcart.Cart;

public interface CartDAO {

    public void addCart(Cart cart);

    public Cart getCart(int cartId);

    public Cart updateCart(Cart cart);

    public void removeCart(int cartId);

    public void unassignCart(int cartId);


}
