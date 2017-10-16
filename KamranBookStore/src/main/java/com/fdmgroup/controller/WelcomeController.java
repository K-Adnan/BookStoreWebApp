package com.fdmgroup.controller;


import java.security.Principal;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.entities.User;

@Controller
public class WelcomeController {
	
	@Autowired
	private EntityManagerFactory factory;
	
	@Autowired
	private UserDAO userDao;
	
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
		
		User user = userDao.getUser(emailAddress);
		
		model.addAttribute("user", user);
		
		return "ViewPersonalDetails";
	}
	
	@RequestMapping("/adminCheck")
	public String goToAdmin(Model model){
		return "admin/AdminCheck";
	}
	
	
}
