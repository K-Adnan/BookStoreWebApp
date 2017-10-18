package com.fdmgroup.daos;

import java.util.List;

import com.fdmgroup.entities.UnapprovedAuthor;
import com.fdmgroup.entities.User;
import com.fdmgroup.exceptions.EntryAlreadyExistsException;
import com.fdmgroup.exceptions.NoSuchEntryException;

public interface UnapprovedAuthorDAO {
	
	public void addUnapprovedAuthor(UnapprovedAuthor user);
	
	public UnapprovedAuthor getUnapprovedAuthor(String emailAddress);
	
	public void removeUnapprovedAuthor(String emailAddress);
	
	public List<UnapprovedAuthor> getAllUnapprovedAuthors();

}
