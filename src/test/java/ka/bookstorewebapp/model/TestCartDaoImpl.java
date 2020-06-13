package ka.bookstorewebapp.model;

import ka.bookstorewebapp.daos.CartDAO;
import ka.bookstorewebapp.daos.CartDaoImpl;
import ka.bookstorewebapp.entities.User;
import ka.bookstorewebapp.shoppingcart.Cart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestCartDaoImpl {

    private CartDAO cartDao;

    @Mock
    private EntityManager manager;
    private EntityTransaction transaction;
    private EntityManagerFactory factory;
    private TypedQuery typedQuery;

    @Before
    public void setUp() {
        factory = mock(EntityManagerFactory.class);
        manager = mock(EntityManager.class);
        transaction = mock(EntityTransaction.class);
        cartDao = new CartDaoImpl(factory);
        typedQuery = mock(TypedQuery.class);
        when(factory.createEntityManager()).thenReturn(manager);
        when(manager.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void test_AddCartMethod_CallsThePersistMethodInManager() {
        CartDaoImpl cartDaoImpl = new CartDaoImpl();
        Cart cart = new Cart();
        cartDao.addCart(cart);

        verify(transaction, times(1)).begin();
        verify(manager, times(1)).persist(cart);
        verify(transaction, times(1)).commit();
    }

    @Test
    public void test_GetCartMethod_CallsTheFindMethodInManager() {
        Cart cart = new Cart();
        when(manager.find(Cart.class, 2)).thenReturn(cart);
        Assert.assertEquals(cart, cartDao.getCart(2));
    }

    @Test
    public void test_UpdateCartMethod_CallsMergeMethodInManager() {
        Cart cart = new Cart();
        cartDao.updateCart(cart);

        verify(manager, times(1)).merge(cart);
    }

    @Test
    public void test_RemoveCartMethod_CallsTheRemoveMethodInManager() {
        Cart cart = new Cart();
        when(manager.find(Cart.class, 3)).thenReturn(cart);
        cartDao.removeCart(3);
        verify(manager).remove(cart);
    }

    @Test
    public void test_UnassignCartMethod_UnassignsUserFromCart() {
        User user = new User();
        Cart cart = new Cart();
        cart.setCartId(4);
        cart.setUser(user);

        when(manager.find(Cart.class, 4)).thenReturn(cart);

        cart.setUser(user);
        user.setCart(cart);

        cartDao.unassignCart(4);

        verify(manager).merge(user);

    }

}
