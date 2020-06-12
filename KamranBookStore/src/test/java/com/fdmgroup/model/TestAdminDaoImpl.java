package com.fdmgroup.model;

import com.fdmgroup.daos.AdminDAO;
import com.fdmgroup.daos.AdminDaoImpl;
import com.fdmgroup.entities.Admin;
import com.fdmgroup.entities.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestAdminDaoImpl {

    private AdminDAO adminDao;

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
        adminDao = new AdminDaoImpl(factory);
        typedQuery = mock(TypedQuery.class);
        when(factory.createEntityManager()).thenReturn(manager);
        when(manager.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void test_AddAdmin_CallsFindMethodAdminMethod() {
        AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
        Admin admin = new Admin();
        admin.setUserEmail("abc@hotmail.com");
        adminDao.addAdmin(admin);

        verify(manager).persist(admin);
    }

    @Test
    public void test_CallingTheGetAdminMethod_CallsFindMethodOnManager() {
        adminDao.getAdmin("abc@gmail.com");

        verify(manager, times(1)).find(Admin.class, "abc@gmail.com");
    }

    @Test
    public void test_CallingRemoveAdminMethod_CallsGetAdminMethodInManager() {
        adminDao.removeAdmin("def@yahoo.com");

        verify(transaction, times(1)).begin();
        verify(manager, times(1)).find(Admin.class, "def@yahoo.com");
        verify(transaction, times(1)).commit();
    }

    @Test
    public void test_CallingRemoveAdminMethod_CallsRemoveMethodInManager() {
        Admin admin = new Admin();

        when(manager.find(Admin.class, "def@gmail.com")).thenReturn(admin);

        adminDao.removeAdmin("def@gmail.com");

        verify(manager, times(1)).remove(admin);
    }

    @Test
    public void test_UpdateAdmin_CallsMergeMethodOnEntityManager() {
        Admin admin = new Admin();
        adminDao.updateAdmin(admin);
        verify(manager).merge(admin);
    }

    @Test
    public void test_GetAllAdmins_ReturnsListOfAllAdmins() {
        when(manager.createQuery("select a from Admin a", Admin.class)).thenReturn(typedQuery);
        List<Book> list = new ArrayList<Book>();
        when(typedQuery.getResultList()).thenReturn(new ArrayList<Book>());

        adminDao.getAllAdmins();

        verify(manager, times(1)).createQuery("select a from Admin a", Admin.class);
        assertEquals(list, adminDao.getAllAdmins());
    }


}
