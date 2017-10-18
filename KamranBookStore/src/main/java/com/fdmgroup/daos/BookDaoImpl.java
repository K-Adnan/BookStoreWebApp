package com.fdmgroup.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.jpa.internal.EntityManagerFactoryRegistry;
import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.entities.Author;
import com.fdmgroup.entities.Book;
import com.fdmgroup.exceptions.EntryAlreadyExistsException;
import com.fdmgroup.exceptions.NoSuchEntryException;

public class BookDaoImpl implements BookDAO {

	@Autowired
	private EntityManagerFactory factory;

	public BookDaoImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}
	
	public BookDaoImpl(){
	}

	public void addBook(Book newBook) throws EntryAlreadyExistsException{
		EntityManager manager = factory.createEntityManager();

			try {
				manager.getTransaction().begin();
				manager.persist(newBook);
				manager.getTransaction().commit();
			} catch (PersistenceException p) {
				throw new EntryAlreadyExistsException("Book with ISBN " + newBook.getIsbn() +" already exists");
			}
	}
	
	public void updateBook(Book newBook){
		EntityManager manager = factory.createEntityManager();
		
			manager.getTransaction().begin();
			manager.merge(newBook);
			manager.getTransaction().commit();
	}

	public Book getBook(long isbn) throws NoSuchEntryException{
		EntityManager manager = factory.createEntityManager();
		
		Book book;
		book = manager.find(Book.class, isbn);
		
		if (book == null){
			throw new NoSuchEntryException("Book with ISBN " + isbn + " does not exist");
		}
		
		return book;
	}

	public boolean removeBook(long isbn) throws NoSuchEntryException {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Book book = null;

		try {
			book = manager.find(Book.class, isbn);
			manager.remove(book);
		} catch (IllegalArgumentException e) {
			throw new NoSuchEntryException("Book with ISBN " + isbn + " does not exist");
		}

		manager.getTransaction().commit();

		return true;

	}

	public List<Book> getAllBooks() {
		EntityManager manager = factory.createEntityManager();
		TypedQuery<Book> query = manager.createQuery("select b from Book b", Book.class);
		List<Book> listOfAllBooks = query.getResultList();

		return listOfAllBooks;
	}
	
	public List<Book> getBooksByCategory(String category){
		EntityManager manager = factory.createEntityManager();
		TypedQuery<Book> query = manager.createQuery("select b from Book b where b.category = ?", Book.class);
		query.setParameter(1, category);
		List<Book> listOfBooks = query.getResultList();
		
		return listOfBooks;
	}
	
	public List<Book> getBooksByAuthor(String emailAddress){
		EntityManager manager = factory.createEntityManager();
		
		TypedQuery<Book> query = manager.createQuery("select b from Book as b join fetch b.authors a where a.emailAddress = ?", Book.class);
		query.setParameter(1, emailAddress);
		List<Book> listOfBooks = query.getResultList();
		
		return listOfBooks;
	}
	
	public List<Book> getBooksByAllAttributes(String title, String author, String category, Double min, Double max){
		EntityManager manager = factory.createEntityManager();
		TypedQuery<Book> query = manager.createQuery("select b from Book b join fetch b.authors a where b.title like ? AND b.category like ? AND b.price < ? AND b.price > ? AND (a.firstName like ? OR a.lastName like ?)", Book.class);
		query.setParameter(1, "%" + title + "%");
		query.setParameter(2, "%" + category + "%");
		query.setParameter(3, max);
		query.setParameter(4, min);
		query.setParameter(5, "%" + author + "%");
		query.setParameter(6, "%" + author + "%");
		List<Book> listOfBooks = query.getResultList();
		System.out.println(listOfBooks);
		return listOfBooks;
	}
	
}
