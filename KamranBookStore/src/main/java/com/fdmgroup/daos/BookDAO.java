package com.fdmgroup.daos;

import com.fdmgroup.entities.Book;
import com.fdmgroup.exceptions.EntryAlreadyExistsException;
import com.fdmgroup.exceptions.NoSuchEntryException;

import java.util.List;

public interface BookDAO {

    public void addBook(Book book) throws EntryAlreadyExistsException;

    public void updateBook(Book book);

    public Book getBook(long isbn) throws NoSuchEntryException;

    public boolean removeBook(long isbn) throws NoSuchEntryException;

    public List<Book> getAllBooks();

    public List<Book> getBooksByCategory(String string);

    public List<Book> getBooksByAuthor(String emailAddress);

    public List<Book> getBooksByAllAttributes(String title, String author, String category, Double min, Double max);

    public List<Book> getBooksByTitle(String title);

    public List<Book> getBooksByAllAttributes(String search);

}
