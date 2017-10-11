package com.fdmgroup.daos;

import java.util.List;

import com.fdmgroup.entities.Author;

public interface AuthorDAO {
	
	public void addAuthor(Author author);
	
	public Author getAuthor(String emailAddress);
	
	public void updateAuthor(Author author);
	
	public boolean removeAuthor(String emailAddress);
	
	public List<Author> getAllAuthors();

}
