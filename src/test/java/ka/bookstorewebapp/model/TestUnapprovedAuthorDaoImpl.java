package ka.bookstorewebapp.model;

import ka.bookstorewebapp.daos.UnapprovedAuthorDAO;
import ka.bookstorewebapp.daos.UnapprovedAuthorDaoImpl;
import ka.bookstorewebapp.entities.UnapprovedAuthor;
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

public class TestUnapprovedAuthorDaoImpl {

    private UnapprovedAuthorDAO unapprovedAuthorDao;

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
        unapprovedAuthorDao = new UnapprovedAuthorDaoImpl(factory);
        typedQuery = mock(TypedQuery.class);
        when(factory.createEntityManager()).thenReturn(manager);
        when(manager.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void test_AddDepartment_InvokesTransactionMethodAndPersist() {
        UnapprovedAuthor unapprovedAuthor = new UnapprovedAuthor();
        unapprovedAuthorDao.addUnapprovedAuthor(unapprovedAuthor);

        verify(transaction, times(1)).begin();
        verify(manager, times(1)).persist(unapprovedAuthor);
        verify(transaction, times(1)).commit();
    }

    @Test
    public void test_PersistMethodIsNotCalled_WhenUnapprovedAuthorAlreadyExistsInDatabase() {
        UnapprovedAuthor unapprovedAuthor = new UnapprovedAuthor();
        unapprovedAuthor.setEmailAddress("def@hotmail.com");
        when(manager.find(UnapprovedAuthor.class, "def@hotmail.com")).thenReturn(new UnapprovedAuthor());

        unapprovedAuthorDao.addUnapprovedAuthor(unapprovedAuthor);

        verify(transaction, times(1)).begin();
        verify(manager, times(1)).persist(unapprovedAuthor);
        verify(transaction, times(1)).commit();
    }

    @Test
    public void test_CallingTheGetUnapprovedAuthorMethod_CallsFindMethodOnManager() {
        unapprovedAuthorDao.getUnapprovedAuthor("abc@gmail.com");

        verify(manager, times(1)).find(UnapprovedAuthor.class, "abc@gmail.com");
    }

    @Test
    public void test_CallingRemoveUnapprovedAuthorMethod_CallsGetUnapprovedAuthorMethodInManager() {
        unapprovedAuthorDao.removeUnapprovedAuthor("def@yahoo.com");

        verify(transaction, times(1)).begin();
        verify(manager, times(1)).find(UnapprovedAuthor.class, "def@yahoo.com");
        verify(transaction, times(1)).commit();
    }

    @Test
    public void test_CallingRemoveUnapprovedAuthorMethod_CallsRemoveMethodInManager() {
        UnapprovedAuthor unapprovedAuthor = new UnapprovedAuthor();

        when(manager.find(UnapprovedAuthor.class, "def@gmail.com")).thenReturn(unapprovedAuthor);

        unapprovedAuthorDao.removeUnapprovedAuthor("def@gmail.com");

        verify(manager, times(1)).remove(unapprovedAuthor);
    }

    @Test
    public void test_CallingGetAllUnapprovedAuthors_CallsCreateQueryMethodInManager() {
        UnapprovedAuthorDaoImpl unapprovedAuthorDaoImpl = new UnapprovedAuthorDaoImpl();
        when(manager.createQuery("select u from UnapprovedAuthor u", UnapprovedAuthor.class)).thenReturn(typedQuery);

        List<UnapprovedAuthor> list = new ArrayList<UnapprovedAuthor>();
        when(typedQuery.getResultList()).thenReturn(new ArrayList<UnapprovedAuthor>());

        unapprovedAuthorDao.getAllUnapprovedAuthors();

        verify(manager, times(1)).createQuery("select u from UnapprovedAuthor u", UnapprovedAuthor.class);
        assertEquals(list, unapprovedAuthorDao.getAllUnapprovedAuthors());
    }

}
