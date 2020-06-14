package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import static ka.bookstorewebapp.utils.Logging.*;

public class AdminDaoImpl implements AdminDAO {

    @Autowired
    private EntityManagerFactory factory;

    public AdminDaoImpl() {
    }

    public AdminDaoImpl(EntityManagerFactory factory) {
        super();
        this.factory = factory;
    }

    public void addAdmin(Admin newAdmin) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(newAdmin);
        manager.getTransaction().commit();
        info("Saved new Admin to database: " + newAdmin);
    }

    public void updateAdmin(Admin admin) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(admin);
        manager.getTransaction().commit();
        info("Admin details updated: " + admin);
    }

    public Admin getAdmin(String emailAddress) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Admin admin = manager.find(Admin.class, emailAddress);
        info("Admin retrieved from database: " + admin);
        return admin;
    }

    public boolean removeAdmin(String emailAddress) {
        EntityManager manager = factory.createEntityManager();
        Admin admin = manager.find(Admin.class, emailAddress);
        manager.getTransaction().begin();
        manager.remove(admin);
        manager.getTransaction().commit();
        info("Admin removed from database: " + admin);
        return true;
    }

    public List<Admin> getAllAdmins() {
        info("Retrieving all admins...");
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Admin> query = manager.createQuery("select a from Admin a", Admin.class);
        List<Admin> listOfAllAdmins = query.getResultList();
        info("Number of admins retrieved: " + listOfAllAdmins.size());
        return listOfAllAdmins;
    }

}
