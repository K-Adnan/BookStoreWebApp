package com.fdmgroup.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.fdmgroup.daos.BookDAO;
import com.fdmgroup.daos.BookDaoImpl;
import com.fdmgroup.entities.Book;
import com.fdmgroup.exceptions.EntryAlreadyExistsException;

public class TestBookDaoImpl {

	private BookDAO bookDao;

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
		bookDao = new BookDaoImpl(factory);
		typedQuery = mock(TypedQuery.class);
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
	}

	@Test
	public void test_AddDepartment_InvokesTransactionMethodAndPersist() throws EntryAlreadyExistsException {
		Book book = new Book();
		bookDao.addBook(book);

		verify(transaction, times(1)).begin();
		verify(manager, times(1)).persist(book);
		verify(transaction, times(1)).commit();
	}

	@Test
	public void test_AddDepartment_CallsFindMethodBookMethod() throws EntryAlreadyExistsException {
		Book book = new Book(1234567890l);
		bookDao.addBook(book);

		verify(manager).find(Book.class, 1234567890l);
	}
	
	@Test(expected=EntryAlreadyExistsException.class)
	public void test_PersistMethodIsNotCalled_WhenBookAlreadyExistsInDatabase() throws EntryAlreadyExistsException{
		Book book = new Book(9876543210l);
		when(manager.find(Book.class, 9876543210l)).thenReturn(new Book());
		
		bookDao.addBook(book);
		
	}
	
	@Test
	public void test_CallingTheGetBookMethod_CallsFindMethodOnManager(){
		bookDao.getBook(1234567890l);
		
		verify(manager, times(1)).find(Book.class, 1234567890l);
	}
	
	@Test
	public void test_CallingRemoveBookMethod_CallsGetBookMethodInManager(){
		bookDao.removeBook(1234567890l);
		
		verify(transaction, times(1)).begin();
		verify(manager, times(1)).find(Book.class, 1234567890l);
		verify(transaction, times(1)).commit();
	}
	
	@Test
	public void test_CallingRemoveBookMethod_CallsRemoveMethodInManager() {
		Book book = new Book();
		
		when(manager.find(Book.class, 1234567890l)).thenReturn(book);
		
		bookDao.removeBook(1234567890l);
		
		verify(manager, times(1)).remove(book);
	}
	
	@Test
	public void test_CallingGetAllBooks_CallsCreateQueryMethodInManager(){
		when(manager.createQuery("select b from Book b", Book.class)).thenReturn(typedQuery);
		
		List<Book> list = new ArrayList<Book>();
		when(typedQuery.getResultList()).thenReturn(new ArrayList<Book>());
		
		bookDao.getAllBooks();
		
		verify(manager, times(1)).createQuery("select b from Book b", Book.class);
		assertEquals(list, bookDao.getAllBooks());
	}
	
	@Test
	public void test_UpdateBookMethod_CallsTheMergeMethodOnManager(){
		Book book = new Book();
		bookDao.updateBook(book);
		verify(manager, times(1)).merge(book);
	}
	
	@Test
	public void test_RemoveBookOnABookWhichDoesNotExist_ReturnsFalse(){
		when(manager.find(Book.class, 1234567890l)).thenThrow(IllegalArgumentException.class);
		
		boolean returnStatement = bookDao.removeBook(1234567890l);
		
		assertEquals(false, returnStatement);
	}

}
