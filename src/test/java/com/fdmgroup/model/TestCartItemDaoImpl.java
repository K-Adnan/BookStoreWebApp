package com.fdmgroup.model;

import com.fdmgroup.daos.CartItemDAO;
import com.fdmgroup.daos.CartItemDaoImpl;
import com.fdmgroup.entities.User;
import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.CartItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestCartItemDaoImpl {

    private CartItemDAO cartItemDao;

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
        cartItemDao = new CartItemDaoImpl(factory);
        typedQuery = mock(TypedQuery.class);
        when(factory.createEntityManager()).thenReturn(manager);
        when(manager.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void test_GetCartItemMethod_CallsTheFindMethodInManager() {
        CartItemDaoImpl cartItemDaoImpl = new CartItemDaoImpl();
        CartItem cartItem = new CartItem();
        when(manager.find(CartItem.class, 2)).thenReturn(cartItem);
        assertEquals(cartItem, cartItemDao.getCartItem(2));
    }

    @Test
    public void test_UpdateCartItemMethod_CallsMergeMethodInManager() {
        CartItem cartItem = new CartItem();
        cartItemDao.updateCartItem(cartItem);

        verify(manager, times(1)).merge(cartItem);
    }

    @Test
    public void test_RemoveCartItemMethod_CallsTheRemoveMethodInManager() {
        CartItem cartItem = new CartItem();
        when(manager.find(CartItem.class, 3)).thenReturn(cartItem);
        cartItemDao.removeCartItem(3);
        verify(manager).remove(cartItem);
    }

    @Test
    public void test_GetAllCartItemsForCartMethod_ReturnsListOfAllCartItems() {
        Cart cart = new Cart();
        List<CartItem> cartItems = new ArrayList<CartItem>();

        List<User> list = new ArrayList<User>();
        when(manager.createQuery("Select c from CartItem c where c.cartId = ?", CartItem.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(cartItems);

        cartItemDao.getAllCartItemsForCart(cart);

        verify(manager, times(1)).createQuery("Select c from CartItem c where c.cartId = ?", CartItem.class);
        assertEquals(cartItems, cartItemDao.getAllCartItemsForCart(cart));
    }

    @Test
    public void test_AddCartItemMethod_CallsPersistInManager_WhenCartItemDoesNotExist() {
        CartItem cartItem = new CartItem();
        Cart cart = new Cart();

        cartItem.setCart(cart);

        cartItemDao.addCartItem(cartItem);

        verify(manager).persist(cartItem);
    }

}
