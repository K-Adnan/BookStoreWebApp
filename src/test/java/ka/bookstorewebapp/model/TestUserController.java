package ka.bookstorewebapp.model;

import ka.bookstorewebapp.controller.UserController;
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

public class TestUserController {

    UserController userController;
    String emailAddress;
    User.UserBuilder userBuilder;

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
    RejectedUserDAO rejectedUserDao;
    UnapprovedAuthor ua;
    RejectedUser rj;

    @Before
    public void setUp() {
        emailAddress = "abc@gmail.com";
        rj = mock(RejectedUser.class);
        ua = mock(UnapprovedAuthor.class);
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
        principal = mock(Principal.class);
        req = mock(HttpServletRequest.class);
        book = mock(Book.class);
        session = mock(HttpSession.class);
        user = mock(User.class);
        manager = mock(EntityManager.class);
        rejectedUserDao = mock(RejectedUserDAO.class);
        factory = mock(EntityManagerFactory.class);
        when(factory.createEntityManager()).thenReturn(manager);
        userDao = mock(UserDAO.class);
        userController = new UserController(userDao, unapprovedAuthorDao, rejectedUserDao, authorDao);
        userBuilder = new User().toBuilder();
        when(user.toBuilder()).thenReturn(userBuilder);
    }

    @Test
    public void test_GoToViewUser_ReturnsAdminViewUser() {
        assertEquals("admin/ViewUser", userController.goToViewUser());
    }

    @Test
    public void test_GoToDisplayUser_ReturnsAdminDisplayUser() {
        assertEquals("admin/DisplayUser", userController.goToDisplayUser(emailAddress, model));
    }

    @Test
    public void test_GoToEditUser_ReturnsAdminEditUser() {
        assertEquals("admin/EditUser", userController.goToEditUser(model, emailAddress));
    }

    @Test
    public void test_GoToEditPersonalDetails_ReturnsEditPersonalDetails() {
        assertEquals("EditPersonalDetails", userController.goToEditPersonalDetails(model, principal));
    }

    @Test
    public void test_DoUpdatePersonalDetails_ReturnsAdminDisplayUser() {
        when(principal.getName()).thenReturn(emailAddress);
        when(userDao.getUser(emailAddress)).thenReturn(user);
        assertEquals("ViewPersonalDetails", userController.doUpdatePersonalDetails(user, principal, model));
    }

    @Test
    public void test_DoUpdateProfile_ReturnsAdminDisplayUser() {
        when(user.getEmailAddress()).thenReturn(emailAddress);
        when(userDao.getUser(emailAddress)).thenReturn(user);
        assertEquals("admin/DisplayUser", userController.doUpdateProfile(user, model));
    }

    @Test
    public void test_GoToChangePassword_ReturnsChangePassword() {
        assertEquals("ChangePassword", userController.goToChangePassword());
    }

    @Test
    public void test_DoChangePassword_ReturnsViewPersonalDetails_WhenCorrectDetailsAreEntered() {
        when(principal.getName()).thenReturn(emailAddress);
        when(userDao.getUser(emailAddress)).thenReturn(user);
        when(user.getPassword()).thenReturn("abc");
        assertEquals("ViewPersonalDetails", userController.doChangePassword("abc", "def", "def", principal, model));
    }

    @Test
    public void test_DoChangePassword_ReturnsChangePassword_WhenIncorrectDetailsAreEntered() {
        when(principal.getName()).thenReturn(emailAddress);
        when(userDao.getUser(emailAddress)).thenReturn(user);
        assertEquals("ChangePassword", userController.doChangePassword("abc", "def", "def", principal, model));
    }

    @Test
    public void test_DoChangePassword_ReturnsChangePassword_WhenPasswordAndConfirmPasswordDoNotMatch() {
        when(principal.getName()).thenReturn(emailAddress);
        when(userDao.getUser(emailAddress)).thenReturn(user);
        when(user.getPassword()).thenReturn("abc");
        assertEquals("ChangePassword", userController.doChangePassword("abc", "def", "deg", principal, model));
    }

    @Test
    public void test_DoApproveRequest_ReturnsAdminViewAuthorRequests() {
        when(unapprovedAuthorDao.getUnapprovedAuthor(emailAddress)).thenReturn(ua);
        assertEquals("admin/ViewAuthorRequests", userController.doApproveRequest(model, emailAddress));
    }

    @Test
    public void test_DoRejectRequest_ReturnsAdminViewAuthorRequests() {
        when(unapprovedAuthorDao.getUnapprovedAuthor(emailAddress)).thenReturn(ua);
        assertEquals("admin/ViewAuthorRequests", userController.doRejectRequest(model, emailAddress, req));
    }

    @Test
    public void test_GoToViewRejectedAuthors_ReturnsAdminViewRejectedAuthors() {
        assertEquals("admin/ViewRejectedAuthors", userController.goToViewRejectedAuthors(model));
    }

}
