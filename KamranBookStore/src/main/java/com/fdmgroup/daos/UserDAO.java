package com.fdmgroup.daos;

import java.util.List;

import com.fdmgroup.entities.User;
import com.fdmgroup.exceptions.EntryAlreadyExistsException;
import com.fdmgroup.exceptions.NoSuchEntryException;

public interface UserDAO {
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public User getUser(String emailAddress);
	
	public void removeUser(String emailAddress);
	
	public List<User> getAllUsers();

}
