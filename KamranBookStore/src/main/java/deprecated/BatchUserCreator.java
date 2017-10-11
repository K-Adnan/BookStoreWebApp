package deprecated;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.daos.UserDaoImpl;
import com.fdmgroup.entities.User;

public class BatchUserCreator {
	
	public static void main(String[] args) {
		UserFileReader userFileReader = new UserFileReader();
		List<User> listOfUsers = userFileReader.readUsersFromFile();
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		UserDAO userDao = new UserDaoImpl(factory);
		
		for (User user : listOfUsers){
			userDao.addUser(user);
		}
		
	}

}
