package com.fdmgroup.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;

import com.fdmgroup.controller.RegistrationController;
import com.fdmgroup.daos.AdminDAO;
import com.fdmgroup.daos.AuthorDAO;
import com.fdmgroup.daos.BookDAO;
import com.fdmgroup.daos.CartDAO;
import com.fdmgroup.daos.CartItemDAO;
import com.fdmgroup.daos.OrderDAO;
import com.fdmgroup.daos.UnapprovedAuthorDAO;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.entities.Admin;
import com.fdmgroup.entities.Author;
import com.fdmgroup.entities.Book;
import com.fdmgroup.entities.UnapprovedAuthor;
import com.fdmgroup.entities.User;
import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.CartItem;

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
