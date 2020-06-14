package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.entities.UnapprovedAuthor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

import static ka.bookstorewebapp.utils.Logging.info;

public class UnapprovedAuthorDaoImpl implements UnapprovedAuthorDAO {

    @Autowired
    private EntityManagerFactory factory;

    public UnapprovedAuthorDaoImpl(EntityManagerFactory factory) {
        super();
        this.factory = factory;
    }

    public UnapprovedAuthorDaoImpl() {
    }

    public void addUnapprovedAuthor(UnapprovedAuthor newUnapprovedAuthor) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(newUnapprovedAuthor);
        manager.getTransaction().commit();
        info("New unapproved author added to the database: " + newUnapprovedAuthor, getClass());
    }

    public UnapprovedAuthor getUnapprovedAuthor(String emailAddress) {
        EntityManager manager = factory.createEntityManager();
        UnapprovedAuthor unApprovedAuthor = manager.find(UnapprovedAuthor.class, emailAddress);
        info("Retrieved unapproved author: " + unApprovedAuthor, getClass());
        return unApprovedAuthor;
    }

    public void removeUnapprovedAuthor(String emailAddress) {
        info("Removing unapproved author with email address: " + emailAddress, getClass());
        EntityManager manager = factory.createEntityManager();
        UnapprovedAuthor unApprovedAuthor = manager.find(UnapprovedAuthor.class, emailAddress);
        manager.getTransaction().begin();
        manager.remove(unApprovedAuthor);
        manager.getTransaction().commit();
        info("UnapprovedAuthor removed with email address: " + emailAddress, getClass());
    }

    public List<UnapprovedAuthor> getAllUnapprovedAuthors() {
        info("Retrieving all unapproved authors...", getClass());
        EntityManager manager = factory.createEntityManager();
        TypedQuery<UnapprovedAuthor> query = manager.createQuery("select u from UnapprovedAuthor u", UnapprovedAuthor.class);
        List<UnapprovedAuthor> listOfAllUnapprovedAuthors = query.getResultList();
        info("Successfully retrieved all unapproved authors", getClass());
        info("Number of unapproved authors retrieved: " + listOfAllUnapprovedAuthors.size(), getClass());
        return listOfAllUnapprovedAuthors;
    }

}
