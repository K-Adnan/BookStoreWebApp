package com.fdmgroup.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.daos.AuthorDAO;
import com.fdmgroup.daos.AuthorDaoImpl;
import com.fdmgroup.daos.BookDAO;
import com.fdmgroup.daos.BookDaoImpl;
import com.fdmgroup.daos.CartDAO;
import com.fdmgroup.daos.CartDaoImpl;
import com.fdmgroup.daos.CartItemDAO;
import com.fdmgroup.daos.CartItemDaoImpl;
import com.fdmgroup.daos.OrderDAO;
import com.fdmgroup.daos.OrderDaoImpl;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.daos.UserDaoImpl;
import com.fdmgroup.entities.Author;
import com.fdmgroup.entities.Book;

public class SoloProjectRunner {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		UserDAO userDao = new UserDaoImpl(factory);
		BookDAO bookDao = new BookDaoImpl(factory);
		AuthorDAO authorDao = new AuthorDaoImpl(factory);
		CartItemDAO cartItemDao = new CartItemDaoImpl(factory);
		CartDAO cartDao = new CartDaoImpl(factory);
		OrderDAO orderDao = new OrderDaoImpl(factory);
		
		Book book = new Book();
		book.setIsbn(1234);
		
		String authorString = "adam.kay,adolf.hitler";
		
		String[] authors = authorString.split(",");
		
		for (String authorStr : authors){
			String emailAddress = authorStr + "@books4u.com";
			Author author = authorDao.getAuthor(emailAddress);
			System.out.println(author);
			book.setAuthor(author);
		}
		
		bookDao.addBook(book);
		
		factory.close();
	}
}
