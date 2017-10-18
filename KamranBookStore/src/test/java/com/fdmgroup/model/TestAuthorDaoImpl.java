package com.fdmgroup.model;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.fdmgroup.daos.AuthorDAO;
import com.fdmgroup.daos.AuthorDaoImpl;
import com.fdmgroup.entities.Author;
import com.fdmgroup.entities.Book;

public class TestAuthorDaoImpl {

	private AuthorDAO authorDao;

	@Mock
	private EntityManager manager;
	private EntityTransaction transaction;
	private EntityManagerFactory factory;
	private TypedQuery typedQuery;

	@Before
	public void setUp() {
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		authorDao = new AuthorDaoImpl(factory);
		typedQuery = mock(TypedQuery.class);
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
	}

	@Test
	public void test_AddAuthor_CallsFindMethodAuthorMethod() {
		AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
		Author author = new Author();
		author.setUserEmail("abc@hotmail.com");
		authorDao.addAuthor(author);

		verify(manager).persist(author);
	}
	
	@Test
	public void test_CallingTheGetAuthorMethod_CallsFindMethodOnManager(){
		authorDao.getAuthor("abc@gmail.com");
		
		verify(manager, times(1)).find(Author.class, "abc@gmail.com");
	}
	
	@Test
	public void test_CallingRemoveAuthorMethod_CallsGetAuthorMethodInManager(){
		authorDao.removeAuthor("def@yahoo.com");
		
		verify(transaction, times(1)).begin();
		verify(manager, times(1)).find(Author.class, "def@yahoo.com");
		verify(transaction, times(1)).commit();
	}
	
	@Test
	public void test_CallingRemoveAuthorMethod_CallsRemoveMethodInManager() {
		Author author = new Author();
		
		when(manager.find(Author.class, "def@gmail.com")).thenReturn(author);
		
		authorDao.removeAuthor("def@gmail.com");
		
		verify(manager, times(1)).remove(author);
	}
	
	@Test
	public void test_UpdateAuthor_CallsMergeMethodOnEntityManager(){
		Author author = new Author();
		authorDao.updateAuthor(author);
		verify(manager).merge(author);
	}
	
	@Test
	public void test_GetAllAuthors_ReturnsListOfAllAuthors(){
		when(manager.createQuery("select a from Author a", Author.class)).thenReturn(typedQuery);
		List<Book> list = new ArrayList<Book>();
		when(typedQuery.getResultList()).thenReturn(new ArrayList<Book>());
		
		authorDao.getAllAuthors();
		
		verify(manager, times(1)).createQuery("select a from Author a", Author.class);
		assertEquals(list, authorDao.getAllAuthors());
	}
	
	
	
	
	
}
