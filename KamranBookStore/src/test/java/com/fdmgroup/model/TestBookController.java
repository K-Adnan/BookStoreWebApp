package com.fdmgroup.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;

import com.fdmgroup.controller.BookController;
import com.fdmgroup.daos.AuthorDAO;
import com.fdmgroup.daos.BookDAO;
import com.fdmgroup.daos.CartDAO;
import com.fdmgroup.daos.CartItemDAO;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.entities.Author;
import com.fdmgroup.entities.Book;
import com.fdmgroup.entities.User;
import com.fdmgroup.exceptions.NoSuchEntryException;

public class TestBookController {
	
	BookController bookController;
	long isbn;
	String emailAddress;
	
	@Mock
	BookDAO bookDao;
	AuthorDAO authorDao;
	Model model;
	Principal principal;
	HttpServletRequest req;
	Book book;
	HttpSession session;
	UserDAO userDao;
	User user;
	EntityManagerFactory factory;
	EntityManager manager;
	CartDAO cartDao;
	CartItemDAO cartItemDao;

	@Before
	public void setUp() {
		bookDao = mock(BookDAO.class);
		cartDao = mock(CartDAO.class);
		authorDao = mock(AuthorDAO.class);
		emailAddress = "abc@gmail.com";
		model = mock(Model.class);
		userDao = mock(UserDAO.class);
		cartItemDao = mock(CartItemDAO.class);
		bookController = new BookController(bookDao, authorDao, userDao, cartDao, cartItemDao);
		isbn = 123;
		principal = mock(Principal.class);
		req = mock(HttpServletRequest.class);
		book = mock(Book.class);
		session = mock(HttpSession.class);
		user = mock(User.class);
		manager = mock(EntityManager.class);
		factory = mock(EntityManagerFactory.class);
		when(factory.createEntityManager()).thenReturn(manager);
	}

	@Test
	public void test_GoToViewAllBooks_ReturnsViewAllBooks() {
		List<Book> list = new ArrayList<Book>();
		when(bookDao.getAllBooks()).thenReturn(list);
		assertEquals("ViewAllBooks", bookController.goToViewAllBooks(model));
	}
	
	@Test
	public void test_GoToDisplayBooks_ReturnsViewBooks() {
		Author author = new Author();
		List<Book> list = new ArrayList<Book>();
		when(bookDao.getAllBooks()).thenReturn(list);
		assertEquals("ViewBooks", bookController.goToDisplayBooks(123l, "Title", "Author", "Category", 0.0, 2.99, model));
	}
	
	@Test
	public void test_GoToDisplayBook_ReturnsViewBook() throws NoSuchEntryException{
		Author author = mock(Author.class);
		Set<Author> listOfAuthors = new HashSet<Author>();
		listOfAuthors.add(author);
		when(book.getAuthors()).thenReturn(listOfAuthors);
		when(author.getEmailAddress()).thenReturn("abc@gmail.com");
		when(bookDao.getBook(isbn)).thenReturn(book);
		when(principal.getName()).thenReturn("abc@gmail.com");
		assertEquals("ViewBook", bookController.goToDisplayBook(isbn, model, principal, req));
	}

	@Test
	public void test_GoToDisplayBook_ReturnsViewBookForAuthorThatDoesNotOwnTheBook() throws NoSuchEntryException{
		Author author = mock(Author.class);
		Set<Author> listOfAuthors = new HashSet<Author>();
		listOfAuthors.add(author);
		when(book.getAuthors()).thenReturn(listOfAuthors);
		when(author.getEmailAddress()).thenReturn("abc@gmail.com");
		when(bookDao.getBook(isbn)).thenReturn(book);
		when(principal.getName()).thenReturn("abdc@gmail.com");
		assertEquals("ViewBook", bookController.goToDisplayBook(isbn, model, principal, req));
	}
	
	@Test
	public void test_GoToViewBooksByCategory_ReturnsViewBooks(){
		assertEquals("ViewBooks", bookController.goToViewBooksByCategory("Biography", model));
	}
	
	@Test
	public void test_DoRateBook_ReturnsViewBook() throws NoSuchEntryException{
		when(req.getParameter("rating")).thenReturn("3");
		when(bookDao.getBook(isbn)).thenReturn(new Book());
		assertEquals("ViewBook", bookController.doRateBook(isbn, model, req));
	}
	
	@Test
	public void test_GoToListNewBook_ReturnsAuthorListNewBook(){
		assertEquals("author/ListNewBook", bookController.goToListNewBook(model, principal, req));
	}
	
	@Test
	public void test_DoListNewBook_ReturnsAdminAdminHome(){
		assertEquals("admin/AdminHome", bookController.doListNewBook("abc", model, book, req));
	}
	
	@Test
	public void test_GoToViewSales_ReturnsAuthorViewSales(){
		assertEquals("author/ViewSales", bookController.goToViewSales(model, principal));
	}
	
	@Test
	public void test_GoToEditBook_ReturnsAuthorEditBook(){
		assertEquals("author/EditBook", bookController.goToEditBook(isbn, model, principal, req));
	}
	
	@Test
	public void test_DoEditBook_ReturnsRedirectViewSales() throws NoSuchEntryException{
		when(bookDao.getBook(isbn)).thenReturn(book);
		assertEquals("redirect:viewSales", bookController.doEditBook(isbn, "ABC", book, model, principal, req));
	}
	
	@Test
	public void test_DoAddBookToBasket_ReturnsViewBook() throws NoSuchEntryException{
		when(bookDao.getBook(isbn)).thenReturn(book);
		when(principal.getName()).thenReturn(emailAddress);
		when(userDao.getUser(emailAddress)).thenReturn(user);
		bookController.doAddBookToBasket(isbn, 1, model, principal, session);
		
	}
	
}
