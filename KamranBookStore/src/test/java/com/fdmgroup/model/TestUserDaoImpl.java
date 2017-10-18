package com.fdmgroup.model;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
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

import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.daos.UserDaoImpl;
import com.fdmgroup.entities.User;

public class TestUserDaoImpl {

	private UserDAO userDao;

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
		userDao = new UserDaoImpl(factory);
		typedQuery = mock(TypedQuery.class);
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
	}

	@Test
	public void test_AddUser_InvokesTransactionMethodAndPersist() {
		User user = new User();
		userDao.addUser(user);

		verify(transaction, times(1)).begin();
		verify(manager, times(1)).persist(user);
		verify(transaction, times(1)).commit();
	}

	@Test
	public void test_AddDepartment_CallsFindMethodUserMethod() {
		User user = new User();
		user.setUserEmail("abc@hotmail.com");
		userDao.addUser(user);

		verify(manager).find(User.class, "abc@hotmail.com");
	}
	
	@Test
	public void test_PersistMethodIsNotCalled_WhenUserAlreadyExistsInDatabase(){
		User user = new User();
		user.setUserEmail("def@hotmail.com");
		when(manager.find(User.class, "def@hotmail.com")).thenReturn(new User());
		
		userDao.addUser(user);
		
		verify(transaction, times(0)).begin();
		verify(manager, times(0)).persist(user);
		verify(transaction, times(0)).commit();
	}
	
	@Test
	public void test_CallingTheGetUserMethod_CallsFindMethodOnManager(){
		userDao.getUser("abc@gmail.com");
		
		verify(manager, times(1)).find(User.class, "abc@gmail.com");
	}
	
	@Test
	public void test_CallingRemoveUserMethod_CallsGetUserMethodInManager(){
		userDao.removeUser("def@yahoo.com");
		
		verify(transaction, times(1)).begin();
		verify(manager, times(1)).find(User.class, "def@yahoo.com");
		verify(transaction, times(1)).commit();
	}
	
	@Test
	public void test_CallingRemoveUserMethod_CallsRemoveMethodInManager() {
		User user = new User();
		
		when(manager.find(User.class, "def@gmail.com")).thenReturn(user);
		
		userDao.removeUser("def@gmail.com");
		
		verify(manager, times(1)).remove(user);
	}
	
	@Test
	public void test_CallingGetAllUsers_CallsCreateQueryMethodInManager(){
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		when(manager.createQuery("select u from User u", User.class)).thenReturn(typedQuery);
		
		List<User> list = new ArrayList<User>();
		when(typedQuery.getResultList()).thenReturn(new ArrayList<User>());
		
		userDao.getAllUsers();
		
		verify(manager, times(1)).createQuery("select u from User u", User.class);
		assertEquals(list, userDao.getAllUsers());
	}
	
	@Test
	public void test_UpdateUser_CallsMergeMethodOnEntityManager(){
		User user = new User();
		userDao.updateUser(user);
		verify(manager).merge(user);
	}
	
}
