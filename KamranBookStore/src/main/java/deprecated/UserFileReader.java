package deprecated;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.entities.User;

public class UserFileReader {
	
	public List<User> readUsersFromFile() {
		List<User> listOfUsers = new ArrayList<User>();

		User user= null;
		try {
			FileReader reader = new FileReader("H:/Java/Users Table.csv");
			BufferedReader br = new BufferedReader(reader);

			String line;
			while ((line = br.readLine()) != null) {
				user = createUser(line);
				listOfUsers.add(user);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}

		return listOfUsers;
	}

	private User createUser(String userRow) {
		String[] arr = userRow.split(",");
		
		String email = arr[0];
		String password = arr[1];
		String firstName = arr[2];
		String lastName = arr[3];
		String phoneNumber = arr[4];
		String address= arr[5] + arr[6];

		User user = new User(email, password, firstName, lastName, address, phoneNumber);
		return user;
	}

}
