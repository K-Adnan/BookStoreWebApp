package com.fdmgroup.model;

import com.fdmgroup.controller.WelcomeController;
import com.fdmgroup.daos.*;
import com.fdmgroup.entities.*;
import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.CartItem;
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

public class TestWelcomeController {

    WelcomeController welcomeController;
    String emailAddress;

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
        welcomeController = new WelcomeController(userDao, bookDao, unapprovedAuthorDao);
    }

    @Test
    public void test_GoToIndex_ReturnsIndex() {
        assertEquals("index", welcomeController.goToIndex());
    }

    @Test
    public void test_GoToHome_ReturnsIndex() {
        assertEquals("index", welcomeController.goToHome(emailAddress, session, principal, req));
    }

    @Test
    public void test_GoToHome_ReturnsUserHome_WhenUserIsUser() {
        when(req.isUserInRole("User")).thenReturn(true);
        assertEquals("redirect:user/userHome", welcomeController.goToHome(emailAddress, session, principal, req));
    }

    @Test
    public void test_GoToHome_ReturnsUserHome_WhenUserIsAdmin() {
        when(req.isUserInRole("Admin")).thenReturn(true);
        assertEquals("redirect:admin/adminHome", welcomeController.goToHome(emailAddress, session, principal, req));
    }

    @Test
    public void test_GoToHome_ReturnsUserHome_WhenUserIsAuthor() {
        when(req.isUserInRole("Author")).thenReturn(true);
        assertEquals("redirect:author/authorHome", welcomeController.goToHome(emailAddress, session, principal, req));
    }

    @Test
    public void test_GoToAdminHome_ReturnsAdminAdminHome() {
        assertEquals("admin/AdminHome", welcomeController.goToAdminHome(model, session));
    }

    @Test
    public void test_GoToAdminHome_ReturnsAuthorAuthorHome() {
        assertEquals("author/AuthorHome", welcomeController.goToAuthorHome(model, session));
    }

    @Test
    public void test_GoToAdminHome_ReturnsUserUserHome() {
        assertEquals("user/UserHome", welcomeController.goToUserHome(model, session));
    }

    @Test
    public void test_DoLogout_ReturnsRedirectSlash() {
        assertEquals("redirect:/", welcomeController.doLogOut(model, session));
    }

    @Test
    public void test_GoToRegister_ReturnsRegister() {
        assertEquals("Register", welcomeController.goToRegister(model));
    }

    @Test
    public void test_GoToSearchBook_ReturnsSearchBook() {
        assertEquals("SearchBook", welcomeController.goToSearchBook(model));
    }

    @Test
    public void test_GoToViewPersonalDetails_ReturnsViewPersonalDetails() {
        assertEquals("ViewPersonalDetails", welcomeController.goToViewPersonalDetails(model, principal));
    }

    @Test
    public void test_GoToViewAuthorRequests_ReturnsAdminViewAuthorRequests() {
        assertEquals("admin/ViewAuthorRequests", welcomeController.goToViewAuthorRequests(model));
    }

    @Test
    public void test_GoToViewAllSales_ReturnsAdminViewAllSales() {
        assertEquals("admin/ViewAllSales", welcomeController.goToViewAllSales(model));
    }

}
