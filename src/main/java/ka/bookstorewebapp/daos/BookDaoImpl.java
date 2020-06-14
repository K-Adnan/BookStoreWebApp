package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.entities.Book;
import ka.bookstorewebapp.exceptions.NoSuchEntryException;
import ka.bookstorewebapp.exceptions.RecordAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

import static ka.bookstorewebapp.utils.Logging.info;
import static ka.bookstorewebapp.utils.Logging.warn;


public class BookDaoImpl implements BookDAO {

    @Autowired
    private EntityManagerFactory factory;

    @Autowired
    AuthorDAO authorDAO;

    public BookDaoImpl(EntityManagerFactory factory) {
        super();
        this.factory = factory;
    }

    public BookDaoImpl() {
    }

    public void addBook(Book newBook) throws RecordAlreadyExistsException {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(newBook);
            manager.getTransaction().commit();
        } catch (PersistenceException p) {
            warn("Unable to add new book. Book already exists with ISBN: " + newBook, getClass());
            throw new RecordAlreadyExistsException("Book with ISBN " + newBook.getIsbn() + " already exists");
        }
    }

    public void updateBook(Book book) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(book);
        manager.getTransaction().commit();
        info("Book details updated in the database: " + book, getClass());
    }

    public Book getBook(long isbn) throws NoSuchEntryException {
        info("Retrieving book with ISBN: " + isbn + "...", getClass());
        EntityManager manager = factory.createEntityManager();
        Book book;
        book = manager.find(Book.class, isbn);

        if (book == null) {
            warn("Unable to retrieve book. Book with ISBN does not exist: " + isbn, getClass());
            throw new NoSuchEntryException("Book with ISBN " + isbn + " does not exist");
        }
        info("Book successfully retrieved from database: " + book, getClass());
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
            warn("Unable to remove book. Book with ISBN does not exist: " + isbn, getClass());
            throw new NoSuchEntryException("Book with ISBN " + isbn + " does not exist");
        }
        manager.getTransaction().commit();
        return true;
    }

    public List<Book> getAllBooks() {
        info("Retrieving all books from the database...", getClass());
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Book> query = manager.createQuery("select b from Book b", Book.class);
        List<Book> listOfAllBooks = query.getResultList();
        info("Number of books retrieved from database: " + listOfAllBooks.size(), getClass());
        return listOfAllBooks;
    }

    public List<Book> getBooksByCategory(String category) {
        info("Retrieving all books from the database for category:" + category + "...", getClass());
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Book> query = manager.createQuery("select b from Book b where b.category = ?", Book.class)
                .setParameter(1, category);
        List<Book> listOfBooks = query.getResultList();
        info("Number of books retrieved from database: " + listOfBooks.size(), getClass());
        return listOfBooks;
    }

    public List<Book> getBooksByTitle(String title) {
        info("Retrieving all books from the database with title ':" + title + "'...", getClass());
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Book> query = manager.createQuery("select b from Book b where lower(b.title) like ?", Book.class)
                .setParameter(1, "%" + title.toLowerCase() + "%");
        List<Book> listOfBooks = query.getResultList();
        info("Number of books retrieved from database: " + listOfBooks.size(), getClass());
        return listOfBooks;
    }

    public List<Book> getBooksByAuthor(String emailAddress) {
        info("Retrieving all books from the database for author with email address :" + emailAddress + "...", getClass());
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Book> query = manager.createQuery("select b from Book as b join fetch b.authors a where a.emailAddress = ?", Book.class)
                .setParameter(1, emailAddress);
        List<Book> listOfBooks = query.getResultList();
        info("Number of books retrieved from database: " + listOfBooks.size(), getClass());
        return listOfBooks;
    }

    public List<Book> getBooksByAllAttributes(String title, String author, String category, Double min, Double max) {
        info("Retrieving books based on advanced search...", getClass());
        EntityManager manager = factory.createEntityManager();

        TypedQuery<Book> query = manager.createQuery("select b from Book b join fetch b.authors a where b.title like ? AND b.category like ? AND b.price < ? AND b.price > ? AND (a.firstName like ? OR a.lastName like ?)", Book.class)
                .setParameter(1, "%" + title.toLowerCase() + "%")
                .setParameter(2, "%" + category + "%")
                .setParameter(3, max)
                .setParameter(4, min)
                .setParameter(5, "%" + author.toLowerCase() + "%")
                .setParameter(6, "%" + author.toLowerCase() + "%");

        List<Book> listOfBooks = query.getResultList();
        info("Number of books retrieved from database: " + listOfBooks.size(), getClass());
        return listOfBooks;
    }

    public List<Book> getBooksByAllAttributes(String search) {
        info("Retrieving books based on advanced search...", getClass());
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Book> query = manager.createQuery("select b from Book b join fetch b.authors a where lower(b.title) like ? OR lower(b.category) like ? OR (lower(a.firstName) like ? OR lower(a.lastName) like ?)", Book.class)
                .setParameter(1, "%" + search.toLowerCase() + "%")
                .setParameter(2, "%" + search.toLowerCase() + "%")
                .setParameter(3, "%" + search.toLowerCase() + "%")
                .setParameter(4, "%" + search.toLowerCase() + "%");
        List<Book> listOfBooks = query.getResultList();
        info("Number of books retrieved from database: " + listOfBooks.size(), getClass());
        return listOfBooks;
    }
}
