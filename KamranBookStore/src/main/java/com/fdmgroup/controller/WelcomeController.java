package com.fdmgroup.controller;


import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.daos.BookDAO;
import com.fdmgroup.daos.CartDAO;
import com.fdmgroup.daos.UnapprovedAuthorDAO;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.entities.Book;
import com.fdmgroup.entities.UnapprovedAuthor;
import com.fdmgroup.entities.User;

@Controller
public class WelcomeController {
	
	@Autowired
	private EntityManagerFactory factory;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private CartDAO cartDao;
	
	@Autowired
	private BookDAO bookDao;
	
	@Autowired
	private UnapprovedAuthorDAO unapprovedAuthorDao;
	
	public WelcomeController(){
	}
	
	public WelcomeController(EntityManagerFactory factory, UserDAO userDao, CartDAO cartDao, BookDAO bookDao,
			UnapprovedAuthorDAO unapprovedAuthorDao) {
		super();
		this.factory = factory;
		this.userDao = userDao;
		this.cartDao = cartDao;
		this.bookDao = bookDao;
		this.unapprovedAuthorDao = unapprovedAuthorDao;
	}

	@RequestMapping("/")
	public String goToIndex(){
		return "index";
	}
	
	@RequestMapping("/home")
	public String goToHome(String emailAddress, HttpSession session, Principal principal, HttpServletRequest request){
		session.setAttribute("emailAddress", principal.getName());
		if(request.isUserInRole("User")){
			return "redirect:user/userHome";
		}else if(request.isUserInRole("Admin")){
			return "redirect:admin/adminHome";
		}else if (request.isUserInRole("Author")){
			return "redirect:author/authorHome";
		}
		return "index";
	}
	
	@RequestMapping("/admin/adminHome")
	public String goToAdminHome(Model model, HttpSession session){
		return "admin/AdminHome";
	}
	
	@RequestMapping("/user/userHome")
	public String goToUserHome(Model model, HttpSession session){
		return "user/UserHome";
	}
	
	@RequestMapping("/author/authorHome")
	public String goToAuthorHome(Model model, HttpSession session){
		return "author/AuthorHome";
	}
	
	@RequestMapping("/logout")
	public String doLogOut(Model model, HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/register")
	public String goToRegister(Model model){
		User user = new User();
		model.addAttribute("user", user);
		return "Register";
	}
	
	@RequestMapping("/searchBook")
	public String goToSearchBook(Model model){
		return "SearchBook";
	}
	
	@RequestMapping("/viewPersonalDetails")
	public String goToViewPersonalDetails(Model model, Principal principal){
		String emailAddress = principal.getName();
		
		User user = userDao.getUser(emailAddress);
		
		model.addAttribute("user", user);
		
		return "ViewPersonalDetails";
	}
	
	@RequestMapping("/admin/viewAuthorRequests")
	public String goToViewAuthorRequests(Model model){
		List<UnapprovedAuthor> list = unapprovedAuthorDao.getAllUnapprovedAuthors();
		
		model.addAttribute("list", list);
		
		return "admin/ViewAuthorRequests";
	}
	
	@RequestMapping("/admin/viewAllSales")
	public String goToViewAllSales(Model model){
		
		List<Book> booksList = bookDao.getAllBooks();
		
		model.addAttribute("booksList", booksList);
		
		return "admin/ViewAllSales";
	}
	
	
}
