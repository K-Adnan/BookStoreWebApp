package ka.bookstorewebapp.model;

import ka.bookstorewebapp.controller.CartController;
import ka.bookstorewebapp.entities.Book;
import ka.bookstorewebapp.entities.User;
import ka.bookstorewebapp.shoppingcart.Cart;
import ka.bookstorewebapp.shoppingcart.CartItem;
import ka.bookstorewebapp.daos.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCartController {

    CartController cartController;
    long isbn;

    @Mock
    BookDAO bookDao;
    AuthorDAO authorDao;
    Model model;
    Principal principal;
    HttpServletRequest req;
    Book book;
    Cart cart;
    HttpSession session;
    CartItem cartItem;
    UserDAO userDao;
    User user;
    EntityManagerFactory factory;
    EntityManager manager;
    CartDAO cartDao;
    CartItemDAO cartItemDao;
    OrderDAO orderDao;

    @Before
    public void setUp() {
        bookDao = mock(BookDAO.class);
        authorDao = mock(AuthorDAO.class);
        cart = mock(Cart.class);
        cartDao = mock(CartDAO.class);
        cartItemDao = mock(CartItemDAO.class);
        cartItem = mock(CartItem.class);
        orderDao = mock(OrderDAO.class);
        model = mock(Model.class);
        isbn = 123;
        principal = mock(Principal.class);
        req = mock(HttpServletRequest.class);
        book = mock(Book.class);
        session = mock(HttpSession.class);
        user = mock(User.class);
        manager = mock(EntityManager.class);
        factory = mock(EntityManagerFactory.class);
        when(factory.createEntityManager()).thenReturn(manager);
        userDao = mock(UserDAO.class);
        cartController = new CartController(userDao, cartDao, cartItemDao, orderDao, bookDao);
    }

    @Test
    public void test_GoToViewCart_ReturnsViewAllBooks() {
        Set<CartItem> list = new HashSet<CartItem>();
        list.add(cartItem);

        when(user.getCart()).thenReturn(cart);
        when(cart.getCartItems()).thenReturn(list);
        when(principal.getName()).thenReturn("abc@gmail.com");
        when(userDao.getUser(principal.getName())).thenReturn(user);
        assertEquals("ViewCart", cartController.goToViewCart(model, principal));
    }

    @Test
    public void test_GoToViewCartForUserThatHasNoCart_ReturnsViewAllBooks() {
        when(principal.getName()).thenReturn("abc@gmail.com");
        when(userDao.getUser(principal.getName())).thenReturn(user);
        assertEquals("ViewCart", cartController.goToViewCart(model, principal));
    }

    @Test
    public void test_DoUpdateCartItem_ReturnsViewCart() {
        when(cartItemDao.getCartItem(1)).thenReturn(cartItem);
        when(cartItem.getCart()).thenReturn(cart);
        assertEquals("ViewCart", cartController.doUpdateCartItem(1, 1, model, principal));
    }

    @Test
    public void test_DoUpdateCartItemForQuantityZero_ReturnsViewCart() {
        when(cartItemDao.getCartItem(1)).thenReturn(cartItem);
        when(cartItem.getCart()).thenReturn(cart);
        assertEquals("ViewCart", cartController.doUpdateCartItem(1, 0, model, principal));
    }

    @Test
    public void test_GoToCheckout_ReturnsCheckout() {
        when(cartDao.getCart(1)).thenReturn(cart);
        assertEquals("Checkout", cartController.goToCheckout(1, model, principal));
    }

    @Test
    public void test_DoPlaceOrder_ReturnsOrderConfirmation() {
        Set<CartItem> list = new HashSet<CartItem>();
        list.add(cartItem);

        when(cartDao.getCart(1)).thenReturn(cart);
        when(cart.getCartItems()).thenReturn(list);
        when(cartItem.getBook()).thenReturn(book);

        when(cartDao.getCart(1)).thenReturn(cart);
        assertEquals("OrderConfirmation", cartController.doPlaceOrder(1, model, principal));
    }


    @Test
    public void test_GoToViewOrders_ReturnsViewOrders() {
        when(cartDao.getCart(1)).thenReturn(cart);
        assertEquals("ViewOrders", cartController.goToViewOrders(model, principal));
    }

}
