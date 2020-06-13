package com.fdmgroup.daos;

import com.fdmgroup.entities.Admin;

import java.util.List;

public interface AdminDAO {

    public void addAdmin(Admin admin);

    public Admin getAdmin(String emailAddress);

    public void updateAdmin(Admin admin);

    public boolean removeAdmin(String emailAddress);

    public List<Admin> getAllAdmins();

}
