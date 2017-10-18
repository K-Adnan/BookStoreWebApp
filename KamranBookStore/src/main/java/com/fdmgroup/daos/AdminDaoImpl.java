package com.fdmgroup.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.entities.Admin;
import com.fdmgroup.entities.Author;

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
		System.out.println("SUCCESS: New admin has been added: " + newAdmin);

	}
	
	public void updateAdmin(Admin admin){
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.merge(admin);
		manager.getTransaction().commit();
	}

	public Admin getAdmin(String emailAddress) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Admin admin = manager.find(Admin.class, emailAddress);

		return admin;
	}

	public boolean removeAdmin(String emailAddress){

		EntityManager manager = factory.createEntityManager();

		Admin admin = manager.find(Admin.class, emailAddress);
		manager.getTransaction().begin();

		manager.remove(admin);
		manager.getTransaction().commit();
		System.out.println("Admin with email address '" + emailAddress + "' has been removed.");
		return true;
	}

	public List<Admin> getAllAdmins() {
		EntityManager manager = factory.createEntityManager();
		
		TypedQuery<Admin> query = manager.createQuery("select a from Admin a", Admin.class);
		List<Admin> listOfAllAdmins = query.getResultList();

		return listOfAllAdmins;
	}

}
