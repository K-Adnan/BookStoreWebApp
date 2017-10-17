package com.fdmgroup.controller;


import java.security.Principal;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.daos.CartDAO;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.entities.User;
import com.fdmgroup.shoppingcart.Cart;

@Controller
public class WelcomeController {
	
	@Autowired
	private EntityManagerFactory factory;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private CartDAO cartDao;
	
	@RequestMapping("/")
	public String goToIndex(){
		return "index";
	}
	
	@RequestMapping("/home")
	public String goToHome(String emailAddress, HttpSession session, Principal principal,HttpServletRequest request){
		session.setAttribute("emailAddress", principal.getName());
		if(request.isUserInRole("User")){
			return "redirect:user/userHome";
		}
		else if(request.isUserInRole("Admin")){
			return "redirect:admin/adminHome";
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
	
	@RequestMapping("/admin/adminCheck")
	public String goToAdmin(Model model){
		return "admin/AdminCheck";
	}
	
	
}
