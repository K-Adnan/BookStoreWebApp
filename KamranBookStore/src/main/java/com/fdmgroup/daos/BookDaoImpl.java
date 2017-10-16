package com.fdmgroup.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.hibernate.jpa.internal.EntityManagerFactoryRegistry;
import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.entities.Author;
import com.fdmgroup.entities.Book;
import com.fdmgroup.exceptions.EntryAlreadyExistsException;

public class BookDaoImpl implements BookDAO {

	@Autowired
	private EntityManagerFactory factory;

	public BookDaoImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}
	
	public BookDaoImpl(){
	}

	public void addBook(Book newBook) throws EntryAlreadyExistsException {
		EntityManager manager = factory.createEntityManager();

		if (getBook(newBook.getIsbn()) == null) {

			manager.getTransaction().begin();
			manager.persist(newBook);
			manager.getTransaction().commit();
			System.out.println("SUCCESS: Book added to the database: " + newBook.getTitle());
		}else{
			throw new EntryAlreadyExistsException("Book with ISBN " + newBook.getIsbn() + " already exists in the database");
		}
	}
	
	public void updateBook(Book newBook){
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.merge(newBook);
		manager.getTransaction().commit();
	}

	public Book getBook(long isbn) {
		EntityManager manager = factory.createEntityManager();
		Book book = manager.find(Book.class, isbn);
		return book;
	}

	public boolean removeBook(long isbn) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Book book = null;

		try {
			book = manager.find(Book.class, isbn);
			manager.remove(book);
			System.out.println("Book has been removed");
		} catch (IllegalArgumentException i) {
			return false;
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
	
	public List<Book> getBooksByYear(int year){
		EntityManager manager = factory.createEntityManager();
		TypedQuery<Book> query = manager.createQuery("select b from Book b where b.releaseYear =  ?", Book.class);
		query.setParameter(1, year);
		List<Book> listOfBooks = query.getResultList();
		
		return listOfBooks;
	}
	
	public List<Book> getBooksByAuthor(String name){
		EntityManager manager = factory.createEntityManager();
		
//		TypedQuery<Book> query = manager.createQuery("select b from Book as b where :author MEMBER of b.authors", Book.class);
		TypedQuery<Book> query = manager.createQuery("select b from Book as b join fetch b.authors a where a.firstName = :name OR a.lastName = :name", Book.class);
		query.setParameter("name", name);
		List<Book> listOfBooks = query.getResultList();
		
		return listOfBooks;
	}
	
	public List<Book> getBooksByPrice(Double min, Double max){
		EntityManager manager = factory.createEntityManager();
		
		List<Book> listOfBooks;
		if (max == null){
			TypedQuery<Book> query = manager.createQuery("select b from Book b where b.price > ?", Book.class);
			query.setParameter(1, min);
			listOfBooks = query.getResultList();
		}else if(min == null){
			TypedQuery<Book> query = manager.createQuery("select b from Book b where b.price < ?", Book.class);
			query.setParameter(1, max);
			listOfBooks = query.getResultList();
		}else{
			TypedQuery<Book> query = manager.createQuery("select b from Book b where b.price < ? AND b.price > ?",
					Book.class);
			query.setParameter(1, max);
			query.setParameter(2, min);
			listOfBooks = query.getResultList();
		}
		
		return listOfBooks;
	}
	
	public List<Book> getBooksByTitle(String title){
		System.out.println("Start");
		EntityManager manager = factory.createEntityManager();
		TypedQuery<Book> query = manager.createQuery("select b from Book b where b.title like ?", Book.class);
		query.setParameter(1, "%" + title + "%");
		List<Book> listOfBooks = query.getResultList();
		System.out.println(listOfBooks);
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
