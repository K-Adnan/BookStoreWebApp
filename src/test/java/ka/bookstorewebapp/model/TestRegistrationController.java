package ka.bookstorewebapp.model;

import ka.bookstorewebapp.controller.RegistrationController;
import ka.bookstorewebapp.shoppingcart.Cart;
import ka.bookstorewebapp.shoppingcart.CartItem;
import ka.bookstorewebapp.daos.*;
import ka.bookstorewebapp.entities.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestRegistrationController {

    RegistrationController registrationController;
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
    AdminDAO adminDao;
    UnapprovedAuthorDAO unapprovedAuthorDao;
    EntityManagerFactory factory;
    EntityManager manager;
    CartDAO cartDao;
    CartItemDAO cartItemDao;
    OrderDAO orderDao;
    Author author;

    @Before
    public void setUp() {
        unapprovedAuthorDao = mock(UnapprovedAuthorDAO.class);
        adminDao = mock(AdminDAO.class);
        author = mock(Author.class);
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
        registrationController = new RegistrationController(userDao, adminDao, authorDao, unapprovedAuthorDao);
    }

    @Test
    public void test_GoToRegisterAuthor_ReturnsRegisterAuthor() {
        assertEquals("RegisterAuthor", registrationController.goToRegisterAuthor(model));
    }

    @Test
    public void test_GoToRegisterAdmin_ReturnsAdminRegisterAdmin() {
        assertEquals("/admin/RegisterAdmin", registrationController.goToRegisterAdmin(model));
    }

    @Test
    public void test_DoRegisterUser_ReturnsIndex() {
        assertEquals("index", registrationController.doRegisterUser(user, model));
    }

    @Test
    public void test_DoRegisterAuthor_ReturnsIndex() {
        assertEquals("index", registrationController.doRegisterAuthor(author, model));
    }

    @Test
    public void test_DoRegisterUa_ReturnsIndex() {
        assertEquals("index", registrationController.doRegisterUa(new UnapprovedAuthor(), model));
    }

    @Test
    public void test_GoToRegisterUser_ReturnsRegisterUser() {
        assertEquals("RegisterUser", registrationController.goToRegisterUser(model));
    }

    @Test
    public void test_DoRegisterAdmin_ReturnsIndex() {
        Admin admin = new Admin();
        admin.setFirstName("");
        admin.setLastName("");
        assertEquals("index", registrationController.doRegisterAdmin(admin, model));
    }

}
