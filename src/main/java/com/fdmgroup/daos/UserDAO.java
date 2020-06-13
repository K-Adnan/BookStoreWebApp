package com.fdmgroup.daos;

import com.fdmgroup.entities.User;

import java.util.List;

public interface UserDAO {

    public void addUser(User user);

    public void updateUser(User user);

    public User getUser(String emailAddress);

    public void removeUser(String emailAddress);

    public List<User> getAllUsers();

}
