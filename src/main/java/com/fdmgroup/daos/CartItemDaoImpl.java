package com.fdmgroup.daos;

import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.CartItem;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class CartItemDaoImpl implements CartItemDAO {

    @Autowired
    private EntityManagerFactory factory;

    public CartItemDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public CartItemDaoImpl() {
    }

    public void addCartItem(CartItem cartItem) {
        Long newIsbn = cartItem.getIsbn();

        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        boolean exists = false;
        int quantity = cartItem.getQuantity();

        int cartItemId = 0;
        for (CartItem eachCartItem : cartItem.getCart().getCartItems()) {
            if (newIsbn == eachCartItem.getIsbn()) {
                exists = true;
                cartItemId = eachCartItem.getCartItemId();
                break;
            }
        }

        if (exists == true) {
            CartItem oldCartItem = getCartItem(cartItemId);
            oldCartItem.addToCartItem(quantity);
            manager.merge(oldCartItem);
            manager.getTransaction().commit();
        } else {
            manager.persist(cartItem);
            manager.getTransaction().commit();

        }


    }

    public CartItem getCartItem(int id) {
        EntityManager manager = factory.createEntityManager();
        CartItem cartItem = manager.find(CartItem.class, id);

        return cartItem;
    }

    public void updateCartItem(CartItem cartItem) {
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();
        manager.merge(cartItem);
        manager.getTransaction().commit();
    }

    public void removeCartItem(int cartItemId) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        CartItem cartItem = manager.find(CartItem.class, cartItemId);
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
