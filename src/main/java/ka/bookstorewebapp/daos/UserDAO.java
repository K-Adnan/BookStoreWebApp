package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.entities.User;
import ka.bookstorewebapp.exceptions.RecordAlreadyExistsException;

import java.util.List;

public interface UserDAO {

    public void addUser(User user) throws RecordAlreadyExistsException;

    public void updateUser(User user);

    public User getUser(String emailAddress);

    public void removeUser(String emailAddress);

    public List<User> getAllUsers();

}
