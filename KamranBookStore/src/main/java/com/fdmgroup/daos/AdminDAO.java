package com.fdmgroup.daos;

import java.util.List;

import com.fdmgroup.entities.Admin;

public interface AdminDAO {
	
	public void addAdmin(Admin admin);
	
	public Admin getAdmin(String emailAddress);
	
	public void updateAdmin(Admin admin);
	
	public boolean removeAdmin(String emailAddress);
	
	public List<Admin> getAllAdmins();

}
