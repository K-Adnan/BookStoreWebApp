package deprecated;

import javax.persistence.EntityManagerFactory;

import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.entities.User;
import com.fdmgroup.exceptions.LoginAuthenticationException;

public class LoginAuthenticator {
	
	EntityManagerFactory factory;
	UserDAO userDao;

	public LoginAuthenticator(EntityManagerFactory factory, UserDAO userDao) {
		this.factory = factory;
		this.userDao = userDao;
	}

	public User authenticate(String emailAddress, String password) throws LoginAuthenticationException{
		
		User user = userDao.getUser(emailAddress);
		
		if (user == null){
			throw new LoginAuthenticationException("Invalid Credentials entered. Please try again");
		}else if (! user.getPassword().equals(password)){
			throw new LoginAuthenticationException("Invalid Credentials entered. Please try again");
		}
		
		return user;
	}

}
