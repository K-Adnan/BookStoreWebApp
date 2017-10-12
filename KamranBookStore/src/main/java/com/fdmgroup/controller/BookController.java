package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.daos.BookDAO;
import com.fdmgroup.daos.BookDaoImpl;
import com.fdmgroup.entities.Book;

@Controller
public class BookController {
	
	@RequestMapping("/viewAllBooks")
	public String goToViewAllBooks(Model model){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		BookDAO bookDao = new BookDaoImpl(factory);
		
		List<Book> booksList = bookDao.getAllBooks();
		model.addAttribute("booksList", booksList);
		return "ViewAllBooks";
	}
	
	@RequestMapping("/displayBooks")
	public String goToDisplayBooks(@RequestParam Long isbn, @RequestParam String title, @RequestParam String author, @RequestParam String category, Model model){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		BookDAO bookDao = new BookDaoImpl(factory);

		List<Book> booksList;
		if (isbn != null){
			Book book = bookDao.getBook(isbn);
			booksList = new ArrayList<Book>();
			booksList.add(book);
		}else{
			System.out.println("Running Else part");
			booksList = bookDao.getBooksByAllAttributes(title, category);
		}
		model.addAttribute("booksList", booksList);
		return "ViewBooks";
	}
	
}
