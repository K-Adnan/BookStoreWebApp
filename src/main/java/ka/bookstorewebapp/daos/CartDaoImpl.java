package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.entities.User;
import ka.bookstorewebapp.shoppingcart.Cart;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CartDaoImpl implements CartDAO {

    @Autowired
    private EntityManagerFactory factory;

    public CartDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public CartDaoImpl() {
    }

    public void addCart(Cart cart) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(cart);
        manager.getTransaction().commit();
    }

    public Cart getCart(int id) {
        EntityManager manager = factory.createEntityManager();
        Cart cart = manager.find(Cart.class, id);
        return cart;
    }

    public Cart updateCart(Cart cart) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Cart updatedCart = manager.merge(cart);
        manager.getTransaction().commit();
        return updatedCart;
    }

    public void removeCart(int id) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Cart cart = manager.find(Cart.class, id);
        manager.remove(cart);
        manager.getTransaction().commit();
    }

    public void unassignCart(int cartId) {
        EntityManager manager = factory.createEntityManager();
        Cart cart = getCart(cartId);
        User user = cart.getUser();
        user.setCart(new Cart());
        cart.setUser(null);
        updateCart(cart);

        manager.getTransaction().begin();
        manager.merge(user);
        manager.getTransaction().commit();
    }
}
