package com.fdmgroup.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.entities.Author;
import com.fdmgroup.entities.User;
import com.fdmgroup.exceptions.EntryAlreadyExistsException;

public class AuthorDaoImpl implements AuthorDAO {

	@Autowired
	private EntityManagerFactory factory;

	public AuthorDaoImpl() {
	}

	public AuthorDaoImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}

	public void addAuthor(Author newAuthor) {
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		manager.persist(newAuthor);
		manager.getTransaction().commit();
		System.out.println("SUCCESS: New author has been added: " + newAuthor);

	}
	
	public void updateAuthor(Author author){
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.merge(author);
		manager.getTransaction().commit();
	}

	public Author getAuthor(String emailAddress) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Author author = manager.find(Author.class, emailAddress);

		return author;
	}

	public boolean removeAuthor(String emailAddress){

		EntityManager manager = factory.createEntityManager();

		Author author = manager.find(Author.class, emailAddress);
		manager.getTransaction().begin();

		manager.remove(author);
		manager.getTransaction().commit();
		System.out.println("Author with email address '" + emailAddress + "' has been removed.");
		return true;
	}

	public List<Author> getAllAuthors() {
		EntityManager manager = factory.createEntityManager();
		
		TypedQuery<Author> query = manager.createQuery("select a from Author a", Author.class);
		List<Author> listOfAllAuthors = query.getResultList();

		return listOfAllAuthors;
	}

}
