package com.fdmgroup.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	
	@RequestMapping("/")
	public String goToIndex(){
		return "index";
	}
	
	@RequestMapping("/home")
	public String goToHome(String emailAddress, HttpSession session){
		session.setAttribute("emailAddress", emailAddress);
		return "Home";
	}
	
	@RequestMapping("/logout")
	public String doLogOut(Model model, HttpSession session){
		model.addAttribute("message", "User '" + (String) session.getAttribute("emailAddress") + "' has been correctly logged out");
		session.invalidate();
		return "index";
	}
	
}
