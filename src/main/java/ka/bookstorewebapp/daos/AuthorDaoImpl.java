package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

import static ka.bookstorewebapp.utils.Logging.info;


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
        info("New author has been added: " + newAuthor);
    }

    public void updateAuthor(Author author) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(author);
        manager.getTransaction().commit();
        info("Author details have been updated: " + author);
    }

    public Author getAuthor(String emailAddress) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Author author = manager.find(Author.class, emailAddress);
        info("Author retrieved from database: " + author);
        return author;
    }

    public boolean removeAuthor(String emailAddress) {
        EntityManager manager = factory.createEntityManager();
        Author author = manager.find(Author.class, emailAddress);
        manager.getTransaction().begin();
        manager.remove(author);
        manager.getTransaction().commit();
        info("Author removed from the database: " + author);
        return true;
    }

    public List<Author> getAllAuthors() {
        info("Retrieving all authors from the database...");
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Author> query = manager.createQuery("select a from Author a", Author.class);
        List<Author> listOfAllAuthors = query.getResultList();
        info("Total number of authors retrieved: " + listOfAllAuthors.size());
        return listOfAllAuthors;
    }

}
