package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.entities.Book;
import ka.bookstorewebapp.exceptions.RecordAlreadyExistsException;
import ka.bookstorewebapp.exceptions.NoSuchEntryException;

import java.util.List;

public interface BookDAO {

    public void addBook(Book book) throws RecordAlreadyExistsException;

    public void updateBook(Book book);

    public Book getBook(long isbn) throws NoSuchEntryException;

    public boolean removeBook(long isbn) throws NoSuchEntryException;

    public List<Book> getAllBooks();

    public List<Book> getBooksByCategory(String string);

    public List<Book> getBooksByAuthor(String emailAddress);

    public List<Book> getBooksByAllAttributes(String title, String author, String category, Double min, Double max);

    public List<Book> getBooksByTitle(String title);

    public List<Book> getBooksByAllAttributes(String search);

}
