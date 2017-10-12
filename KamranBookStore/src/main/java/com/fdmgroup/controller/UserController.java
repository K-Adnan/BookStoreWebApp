package com.fdmgroup.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.daos.UserDaoImpl;
import com.fdmgroup.entities.User;

@Controller
public class UserController {
	
	@RequestMapping("/viewUser")
	public String goToViewUser(){
		return "admin/ViewUser";
	}
	
	@RequestMapping("/displayUser")
	public String goToDisplayUser(@RequestParam String emailAddress, Model model){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		
		UserDAO userDao = new UserDaoImpl(factory);
		User user = userDao.getUser(emailAddress);
		model.addAttribute("user", user);
		model.addAttribute("message", "User details for user with email address: " + emailAddress);
		return "admin/DisplayUser";
	}
	
	@RequestMapping("/editUser")
	public String goToEditUser(Model model,@RequestParam String emailAddress){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		
		UserDAO userDao = new UserDaoImpl(factory);
		User user = userDao.getUser(emailAddress);
		model.addAttribute("user", user);
		return "EditUser";
	}
	
	
//	@RequestMapping("/addUser")
//	public String goToAddUser(Model model){
//		User user = new User();
//		model.addAttribute("user", user);
//		return "AddUser";
//	}
//	
//	@RequestMapping("/processAddUser")
//	public String processAddUser(User user, Model model){
//		
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
//		UserDAO userDao = new UserDaoImpl(factory);
//		
//		userDao.addUser(user);
//		model.addAttribute("message", "User added successfully");
//		
//		return "index";
//	}

}
