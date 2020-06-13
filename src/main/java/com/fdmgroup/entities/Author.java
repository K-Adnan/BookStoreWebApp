package com.fdmgroup.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Author")
public class Author extends User {

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "TEST_BOOKS_AUTHORS", joinColumns = {@JoinColumn(name = "AUTHOR")}, inverseJoinColumns = {@JoinColumn(name = "BOOK")})
    private Set<Book> books = new HashSet<Book>();
    private double sales;

    public Author() {
        sales = 0.0;
    }

    public Author(String firstName, String lastName) {
        super();
        setUserEmail(firstName.toLowerCase() + "." + lastName.toLowerCase() + "@books4u.com");
        setFirstName(firstName);
        setLastName(lastName);
    }

    public void setEmailAddress() {
        setUserEmail(getFirstName().toLowerCase() + "." + getLastName().toLowerCase() + "@books4u.com");
    }

    public Author(String userEmail, String firstName, String password, String lastName, String address, String phoneNumber) {
        super(userEmail, password, firstName, lastName, address, phoneNumber);
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

}
