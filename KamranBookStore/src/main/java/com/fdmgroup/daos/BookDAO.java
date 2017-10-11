package com.fdmgroup.daos;

import java.util.List;

import com.fdmgroup.entities.Author;
import com.fdmgroup.entities.Book;
import com.fdmgroup.exceptions.EntryAlreadyExistsException;

public interface BookDAO {
	
	public void addBook(Book book) throws EntryAlreadyExistsException;
	
	public void updateBook(Book book);
	
	public Book getBook(long isbn);
	
	public boolean removeBook(long isbn);
	
	public List<Book> getAllBooks();

	public List<Book> getBooksByCategory(String string);

	public List<Book> getBooksByYear(int year);

	public List<Book> getBooksByAuthor(Author author);
	
	public List<Book> getBooksByPrice(Double minimum, Double maximum);

}
