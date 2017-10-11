package com.fdmgroup.controller;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.daos.AdminDAO;
import com.fdmgroup.daos.AdminDaoImpl;
import com.fdmgroup.daos.AuthorDAO;
import com.fdmgroup.daos.AuthorDaoImpl;
import com.fdmgroup.daos.BookDAO;
import com.fdmgroup.daos.BookDaoImpl;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.daos.UserDaoImpl;
import com.fdmgroup.entities.Admin;
import com.fdmgroup.entities.Author;
import com.fdmgroup.entities.Book;
import com.fdmgroup.entities.User;
import com.fdmgroup.exceptions.EntryAlreadyExistsException;

public class SoloProjectRunner {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		
//		Admin admin = new Admin("admin@books4u.com", "Mike", "123", "Thompson", "30 Goodge Street, London, SE3 2LE", "Male");
//		User user = new User("test.user@gmail.com", "Test", "123", "User", "404 Test Road, Testbridge, TE5 7AB", "Female");
//		
//		UserDAO userDao = new UserDaoImpl(factory);
//		
//		userDao.addUser(user);
		
		AuthorDAO authorDao = new AuthorDaoImpl(factory);
		
//		
//		AdminDAO adminDao = new AdminDaoImpl(factory);
//		adminDao.addAdmin(admin);
		
		Author author = authorDao.getAuthor("kamran.adnan");
		
		
		Book book = new Book(1234567890l, "Java for Dummies", "Technology", 420, 2014, 21.47);
		book.setAuthor(author);
		
		BookDAO bookDao = new BookDaoImpl(factory);
		
		try {
			bookDao.addBook(book);
			System.out.println("Book successfully added");
		} catch (EntryAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		factory.close();
	}
}
