package com.fdmgroup.daos;

import java.util.List;

import com.fdmgroup.entities.RejectedUser;

public interface RejectedUserDAO {
	
	public void addRejectedUser(RejectedUser rejectedUser);
	
	public RejectedUser getRejectedUser(String emailAddress);
	
	public void removeRejectedUser(String emailAddress);
	
	public List<RejectedUser> getAllRejectedUsers();

}