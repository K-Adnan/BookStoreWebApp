package com.fdmgroup.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Author")
public class Author extends User{
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	private Set<Book> books = new HashSet<Book>();
	private double sales;
	
	public Author(){}
	
	public Author(String firstName, String lastName){
		super();
		setUserEmail(firstName.toLowerCase() + "." + lastName.toLowerCase() + "@books4u.com");
		setFirstName(firstName);
		setLastName(lastName);
	}
	
	public Author(String userEmail, String firstName, String password, String lastName, String address, String gender) {
		super(userEmail, password, firstName, lastName, address, gender);
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	public void addBook(Book book){
		books.add(book);
		book.setAuthor(this);
	}

	public double getSales() {
		return sales;
	}
	
	public double setSales(){
		return sales;
	}

}
