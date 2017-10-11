package com.fdmgroup.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Admin")
public class Admin extends User{
	
	public Admin(){}
	
	public Admin(String userEmail, String firstName, String password, String lastName, String address, String phoneNumber) {
		super(userEmail, password, firstName, lastName, address, phoneNumber);
	}
	
	public void setEmailAddress(){
		setUserEmail(getFirstName().toLowerCase() + "." + getLastName().toLowerCase() + "@books4u.com");
	}

}
