package com.fdmgroup.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SoloProjectRunner {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenceInCreateMode");
//		UserDAO userDao = new UserDaoImpl(factory);
//		BookDAO bookDao = new BookDaoImpl(factory);
//		AuthorDAO authorDao = new AuthorDaoImpl(factory);
//		CartItemDAO cartItemDao = new CartItemDaoImpl(factory);
//		CartDAO cartDao = new CartDaoImpl(factory);
//		OrderDAO orderDao = new OrderDaoImpl(factory);
//		UnapprovedAuthorDAO unapprovedAuthor = new UnapprovedAuthorDaoImpl(factory);
//		
//		User user = userDao.getUser("a");
//		
//		user.setFirstName("Arron");
//		userDao.updateUser(user);

        factory.close();
    }
}
