package ka.bookstorewebapp.model;

import ka.bookstorewebapp.daos.BookDAO;
import ka.bookstorewebapp.daos.BookDaoImpl;
import ka.bookstorewebapp.entities.Book;
import ka.bookstorewebapp.exceptions.EntryAlreadyExistsException;
import ka.bookstorewebapp.exceptions.NoSuchEntryException;
import org.junit.Assert;
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
    public void test_CallingRemoveBookMethod_CallsGetBookMethodInManager() throws NoSuchEntryException {
        bookDao.removeBook(1234567890l);

        verify(transaction, times(1)).begin();
        verify(manager, times(1)).find(Book.class, 1234567890l);
        verify(transaction, times(1)).commit();
    }

    @Test
    public void test_CallingRemoveBookMethod_CallsRemoveMethodInManager() throws NoSuchEntryException {
        Book book = new Book();

        when(manager.find(Book.class, 1234567890l)).thenReturn(book);

        bookDao.removeBook(1234567890l);

        verify(manager, times(1)).remove(book);
    }

    @Test
    public void test_CallingGetAllBooks_CallsCreateQueryMethodInManager() {
        when(manager.createQuery("select b from Book b", Book.class)).thenReturn(typedQuery);

        List<Book> list = new ArrayList<Book>();
        when(typedQuery.getResultList()).thenReturn(new ArrayList<Book>());

        bookDao.getAllBooks();

        verify(manager, times(1)).createQuery("select b from Book b", Book.class);
        assertEquals(list, bookDao.getAllBooks());
    }

    @Test
    public void test_UpdateBookMethod_CallsTheMergeMethodOnManager() {
        Book book = new Book();
        bookDao.updateBook(book);
        verify(manager, times(1)).merge(book);
    }

//	@Test(expected=EntryAlreadyExistsException.class)
//	public void test_AddBookMethodForISBNThatAlreadyExists_ThrowsEntryAlreadyExistsException() throws EntryAlreadyExistsException{
//		Book book = new Book();
//		when(manager.persist(book)).thenThrow(new EntryAlreadyExistsException())
//		bookDao.addBook(new Book());
//	}

    @Test
    public void test_GetBookMethod_CallsTheFindMethodOnManager() throws NoSuchEntryException {
        Book book = new Book(123456l);

        when(manager.find(Book.class, 123456l)).thenReturn(book);
        bookDao.getBook(123456l);
        verify(manager).find(Book.class, 123456l);

        Assert.assertEquals(bookDao.getBook(123456l), book);
    }

    @Test(expected = NoSuchEntryException.class)
    public void test_GetBookMethod_ThrowsNoSuchEntryException_WhenBookDoesNotExist() throws NoSuchEntryException {
        when(manager.find(Book.class, 123456l)).thenReturn(null);
        bookDao.getBook(123456l);
    }

//	@Test(expected=NoSuchEntryException.class)
//	public void test_RemoveBookThrowsNoSuchEntryException_WhenBookDoesNotExist() throws NoSuchEntryException{
//		when(manager.remove(new Book())).th
//		bookDao.removeBook(1l);
//	}

    @Test
    public void test_GetBooksByCategory_CallsTheCreateQueryMethodInManagerClass() {
        when(manager.createQuery("select b from Book b where b.category = ?", Book.class)).thenReturn(typedQuery);

        List<Book> list = new ArrayList<Book>();
        when(typedQuery.getResultList()).thenReturn(list);

        bookDao.getBooksByCategory("Biography");

        verify(manager, times(1)).createQuery("select b from Book b where b.category = ?", Book.class);
        assertEquals(list, bookDao.getBooksByCategory("Biography"));
    }

    @Test
    public void test_GetBooksByAuthor_CallsTheCreateQueryMethodInManagerClass() {
        when(manager.createQuery("select b from Book as b join fetch b.authors a where a.emailAddress = ?", Book.class)).thenReturn(typedQuery);

        List<Book> list = new ArrayList<Book>();
        when(typedQuery.getResultList()).thenReturn(list);

        bookDao.getBooksByAuthor("abc@def.com");

        verify(manager, times(1)).createQuery("select b from Book as b join fetch b.authors a where a.emailAddress = ?", Book.class);
        assertEquals(list, bookDao.getBooksByAuthor("abc@def.com"));
    }

    @Test
    public void test_GetBooksByAllAttributes_ReturnsListOfBooks() {

        when(manager.createQuery("select b from Book b join fetch b.authors a where b.title like ? AND b.category like ? AND b.price < ? AND b.price > ? AND (a.firstName like ? OR a.lastName like ?)", Book.class)).thenReturn(typedQuery);

        List<Book> list = new ArrayList<Book>();
        when(typedQuery.getResultList()).thenReturn(list);

        bookDao.getBooksByAllAttributes("Title", "Author", "Category", 0.0, 99.99);

        verify(manager, times(1)).createQuery("select b from Book b join fetch b.authors a where b.title like ? AND b.category like ? AND b.price < ? AND b.price > ? AND (a.firstName like ? OR a.lastName like ?)", Book.class);
        assertEquals(list, bookDao.getBooksByAllAttributes("Title", "Author", "Category", 0.0, 99.99));
    }

    @Test
    public void test_ConstructorDoesNothing() {
        BookDAO bookDaoImpl = new BookDaoImpl();
    }

}
