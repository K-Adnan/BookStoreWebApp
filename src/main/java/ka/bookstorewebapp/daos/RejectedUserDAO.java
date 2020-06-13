package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.entities.RejectedUser;

import java.util.List;

public interface RejectedUserDAO {

    public void addRejectedUser(RejectedUser rejectedUser);

    public RejectedUser getRejectedUser(String emailAddress);

    public void removeRejectedUser(String emailAddress);

    public List<RejectedUser> getAllRejectedUsers();

}
