package com.fdmgroup.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.daos.AdminDAO;
import com.fdmgroup.daos.AdminDaoImpl;
import com.fdmgroup.daos.AuthorDAO;
import com.fdmgroup.daos.AuthorDaoImpl;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.daos.UserDaoImpl;
import com.fdmgroup.entities.Admin;
import com.fdmgroup.entities.Author;
import com.fdmgroup.entities.User;

@Controller
public class RegistrationController {
	
	@RequestMapping("/registerUser")
	public String goToRegisterUser(Model model){
		User user = new User();
		model.addAttribute("user", user);
		return "RegisterUser";
	}
	
	@RequestMapping("/registerAuthor")
	public String goToRegisterAuthor(Model model){
		Author author = new Author();
		model.addAttribute("author", author);
		return "RegisterAuthor";
	}
	
	@RequestMapping("/registerAdmin")
	public String goToRegisterAdmin(Model model){
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return "/admin/RegisterAdmin";
	}
	
	@RequestMapping("/doRegisterUser")
	public String doRegisterUser(User user, Model model){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		UserDAO userDao = new UserDaoImpl(factory);
		userDao.addUser(user);
		model.addAttribute("message", "Signup successful, you can sign in now");
		return "index";
	}
	
	@RequestMapping("/doRegisterAuthor")
	public String doRegisterAuthor(Author author, Model model){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		AuthorDAO authorDao = new AuthorDaoImpl(factory);
		author.setEmailAddress();
		authorDao.addAuthor(author);
		model.addAttribute("message", "Signup successful, you can sign in now");
		return "index";
	}
	
	@RequestMapping("/doRegisterAdmin")
	public String doRegisterAuthor(Admin admin, Model model){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		AdminDAO adminDao = new AdminDaoImpl(factory);
		admin.setEmailAddress();
		adminDao.addAdmin(admin);
		model.addAttribute("message", "Signup successful, you can sign in now");
		return "index";
	}

}