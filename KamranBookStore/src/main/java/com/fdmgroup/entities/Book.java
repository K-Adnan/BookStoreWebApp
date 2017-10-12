package com.fdmgroup.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TEST_BOOKS")
public class Book {

	@Id
	private long isbn;
	private String title;
	private String summary;
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
//	@JoinTable(name = "SOLOPROJECT_AUTHORS_BOOKS",
//    joinColumns = {@JoinColumn(table = "SOLOPROJECT_USERS",
//                               referencedColumnName = "ISBN"),
//                   @JoinColumn(table = "SOLOPROJECT_BOOKS",
//                                referencedColumnName = "EMAILADDRESS", 
//                               nullable = true)})
	private Set<Author> authors = new HashSet<Author>();

	private String category;
	private int numberOfPages;
	private double avgCustomerRating;
	private int numberOfReviews;
	private int quantity;
	private double price;
	private int releaseYear;
	
	public Book(){
		
	}
	
	public Book(long isbn){
		this.isbn = isbn;
	}

	public Book(long isbn, String title, String category, int numberOfPages,
			int releaseYear, double price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.category = category;
		this.numberOfPages = numberOfPages;
		this.releaseYear = releaseYear;
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getIsbn() {
		return isbn;
	}

	public void updateIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void updateTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCategory() {
		return category;
	}

	public void updateCategory(String category) {
		this.category = category;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getQuantity() {
		return quantity;
	}

	public void updateQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAvgCustomerRating() {
		return avgCustomerRating;
	}
	
	public void addCustomerRating(int rating){
		avgCustomerRating = (avgCustomerRating * numberOfReviews + rating) / (numberOfReviews + 1);
		numberOfReviews++;
	}

	@Override
	public String toString() {
		return "[isbn=" + isbn + ", title=" + title + ", author=" + authors + ", price=" + price + ", pages=" + numberOfPages + "]";
	}

	public Set<Author> getAuthor() {
		return authors;
	}

	public void setAuthor(Author author) {
		authors.add(author);
		author.addBook(this);
	}

	public int getYearOfRelease() {
		return releaseYear;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.releaseYear = yearOfRelease;
	}
	
	public Set<Author> getAuthors() {
		return authors;
	}
	
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	
	public int getNumberOfReviews() {
		return numberOfReviews;
	}
	
	public void setNumberOfReviews(int numberOfReviews) {
		this.numberOfReviews = numberOfReviews;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}
	
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setAvgCustomerRating(double avgCustomerRating) {
		this.avgCustomerRating = avgCustomerRating;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}
