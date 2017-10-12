package com.fdmgroup.controller;


import java.security.Principal;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

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
public class WelcomeController {
	
	@RequestMapping("/")
	public String goToIndex(){
		return "index";
	}
	
	@RequestMapping("/home")
	public String goToHome(String emailAddress, HttpSession session, Principal principal){
		session.setAttribute("emailAddress", principal.getName());
		return "Home";
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
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		UserDAO userDao = new UserDaoImpl(factory);
		
		User user = userDao.getUser(emailAddress);
		
		model.addAttribute("user", user);
		
		return "ViewPersonalDetails";
	}
	
}
