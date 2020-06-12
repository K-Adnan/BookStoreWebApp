package com.fdmgroup.daos;

import com.fdmgroup.entities.UnapprovedAuthor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

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
    }

    public UnapprovedAuthor getUnapprovedAuthor(String emailAddress) {
        EntityManager manager = factory.createEntityManager();
        UnapprovedAuthor unApprovedAuthor = manager.find(UnapprovedAuthor.class, emailAddress);

        return unApprovedAuthor;
    }

    public void removeUnapprovedAuthor(String emailAddress) {

        EntityManager manager = factory.createEntityManager();

        UnapprovedAuthor unApprovedAuthor = manager.find(UnapprovedAuthor.class, emailAddress);
        manager.getTransaction().begin();

        manager.remove(unApprovedAuthor);
        manager.getTransaction().commit();
        System.out.println("SUCCESS: UnapprovedAuthor with email address '" + emailAddress + "' has been removed.");
    }

    public List<UnapprovedAuthor> getAllUnapprovedAuthors() {
        EntityManager manager = factory.createEntityManager();

        TypedQuery<UnapprovedAuthor> query = manager.createQuery("select u from UnapprovedAuthor u", UnapprovedAuthor.class);
        List<UnapprovedAuthor> listOfAllUnapprovedAuthors = query.getResultList();

        return listOfAllUnapprovedAuthors;
    }

}
