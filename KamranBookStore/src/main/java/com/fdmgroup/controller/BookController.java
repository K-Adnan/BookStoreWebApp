package com.fdmgroup.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.daos.BookDAO;
import com.fdmgroup.daos.BookDaoImpl;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.daos.UserDaoImpl;
import com.fdmgroup.entities.Book;
import com.fdmgroup.entities.User;
import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.CartItem;

@Controller
public class BookController {
	
	@RequestMapping("/viewAllBooks")
	public String goToViewAllBooks(Model model){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		BookDAO bookDao = new BookDaoImpl(factory);
		
		List<Book> booksList = bookDao.getAllBooks();
		model.addAttribute("booksList", booksList);
		return "ViewAllBooks";
	}
	
	@RequestMapping("/displayBooks")
	public String goToDisplayBooks(@RequestParam Long isbn, @RequestParam String title, @RequestParam String author, @RequestParam String category, @RequestParam Double min, @RequestParam Double max, Model model){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		BookDAO bookDao = new BookDaoImpl(factory);

		List<Book> booksList;
		if (isbn != null){
			Book book = bookDao.getBook(isbn);
			booksList = new ArrayList<Book>();
			booksList.add(book);
		}else{
			System.out.println("Running Else part");
			booksList = bookDao.getBooksByAllAttributes(title, author, category, min, max);
		}
		model.addAttribute("booksList", booksList);
		return "ViewBooks";
	}
	
	@RequestMapping("/displayBook")
	public String goToDisplayBook(@RequestParam Long isbn, Model model){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		BookDAO bookDao = new BookDaoImpl(factory);
		
		Book book = bookDao.getBook(isbn);
		
		model.addAttribute("book", book);
		
		return "ViewBook";
	}
	
	@RequestMapping("/viewBooksByCategory")
	public String goToViewBooksByCategory(@RequestParam String category, Model model){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		BookDAO bookDao = new BookDaoImpl(factory);
		
		List<Book> booksList = bookDao.getBooksByCategory(category);
		
		model.addAttribute("booksList", booksList);
		
		return "ViewBooks";
	}
	
	@RequestMapping("/addBookToBasket")
	public String doAddBookToBasket(@RequestParam Long isbn, Model model, Principal principal){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		BookDAO bookDao = new BookDaoImpl(factory);
		UserDAO userDao = new UserDaoImpl(factory);
		
		User user = userDao.getUser(principal.getName());
		Book book = bookDao.getBook(isbn);
		
		Cart cart = user.getCart();
		cart.addCartItem(new CartItem(book, 1, cart));
		
		model.addAttribute("book", book);
		
		return "ViewBook";
	}
}
