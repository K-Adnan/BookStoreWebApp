package com.fdmgroup.daos;

import com.fdmgroup.entities.RejectedUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class RejectedUserDaoImpl implements RejectedUserDAO {

    @Autowired
    private EntityManagerFactory factory;

    public RejectedUserDaoImpl(EntityManagerFactory factory) {
        super();
        this.factory = factory;
    }

    public RejectedUserDaoImpl() {
    }

    public void addRejectedUser(RejectedUser newRejectedUser) {
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();
        manager.persist(newRejectedUser);
        manager.getTransaction().commit();
    }

    public RejectedUser getRejectedUser(String emailAddress) {
        EntityManager manager = factory.createEntityManager();
        RejectedUser unApprovedAuthor = manager.find(RejectedUser.class, emailAddress);

        return unApprovedAuthor;
    }

    public void removeRejectedUser(String emailAddress) {

        EntityManager manager = factory.createEntityManager();

        RejectedUser unApprovedAuthor = manager.find(RejectedUser.class, emailAddress);
        manager.getTransaction().begin();

        manager.remove(unApprovedAuthor);
        manager.getTransaction().commit();
        System.out.println("SUCCESS: RejectedUser with email address '" + emailAddress + "' has been removed.");
    }

    public List<RejectedUser> getAllRejectedUsers() {
        EntityManager manager = factory.createEntityManager();

        TypedQuery<RejectedUser> query = manager.createQuery("select u from RejectedUser u", RejectedUser.class);
        List<RejectedUser> listOfAllRejectedUsers = query.getResultList();

        return listOfAllRejectedUsers;
    }

}
