package com.fdmgroup.controller;

import java.security.Principal;

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
	
	
	@RequestMapping("/editPersonalDetails")
	public String goToEditPersonalDetails(Model model, Principal principal){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		String emailAddress = principal.getName();
		
		UserDAO userDao = new UserDaoImpl(factory);
		User user = userDao.getUser(emailAddress);
		model.addAttribute("user", user);
		return "EditPersonalDetails";
	}
	
	@RequestMapping("/updatePersonalDetails")
	public String doUpdatePersonalDetails(User user, Principal principal, Model model){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		UserDAO userDao = new UserDaoImpl(factory);
		String emailAddress = principal.getName();
		
		// Retrieving old user, replacing its values with the new user's values, and then merging it.
		User oldUser = userDao.getUser(emailAddress);
		
		oldUser.setFirstName(user.getFirstName());
		oldUser.setLastName(user.getLastName());
		oldUser.setAddress(user.getAddress());
		oldUser.setPhoneNumber(user.getPhoneNumber());
		
		model.addAttribute("message", "User details have been successfully updated");
		model.addAttribute("user", oldUser);
		
		userDao.updateUser(oldUser);
		return "ViewPersonalDetails";
	}
	
	@RequestMapping("/changePassword")
	public String goToChangePassword(){
		return "ChangePassword";
	}
	
	@RequestMapping("/updateNewPassword")
	public String doChangePassword(@RequestParam String currentPassword, @RequestParam String password, @RequestParam String confirmPassword, Principal principal, Model model){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		UserDAO userDao = new UserDaoImpl(factory);
		String emailAddress = principal.getName();
		
		User user = userDao.getUser(emailAddress);
		
		if (currentPassword.equals(user.getPassword())){
		
			if (password.equals(confirmPassword)){
				user.setPassword(password);
				userDao.updateUser(user);
				model.addAttribute("user", user);
				return "ViewPersonalDetails";
			}else{
				model.addAttribute("message", "Password and Confirm Password fields do not match");
				return "ChangePassword";
			}
		}else{
			model.addAttribute("message", "Current password is incorrect");
			return "ChangePassword";
		}
		
	}
	
}
