package ka.bookstorewebapp.controller;

import ka.bookstorewebapp.daos.UserDAO;
import ka.bookstorewebapp.daos.UserDaoImpl;
import ka.bookstorewebapp.entities.User;

import javax.persistence.Persistence;

public class SoloProjectRunner {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDaoImpl(Persistence.createEntityManagerFactory("PersistenceInCreateMode"));
        User oldUser = userDAO.getUser("a");

        System.out.println("--------------");
        System.out.println(oldUser);
        System.out.println(oldUser);
        System.out.println("--------------");

        User user = User.builder()
                .firstName("Kamran Ahmed")
                .lastName("Adnan")
                .emailAddress("muffingg@hotmail.com")
                .address("134 Station Crescent, Ashford, TW15 3HL")
                .password("myPassword10")
                .phoneNumber("07540109941")
                .build();

        System.out.println(user);

        User user2 = user.toBuilder()
                .firstName("Talal Ahmed")
                .build();

        System.out.println(user2);

//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenceInCreateMode");
//		UserDAO userDao = new UserDaoImpl(factory);
//		BookDAO bookDao = new BookDaoImpl(factory);
//		AuthorDAO authorDao = new AuthorDaoImpl(factory);
//		CartItemDAO cartItemDao = new CartItemDaoImpl(factory);
//		CartDAO cartDao = new CartDaoImpl(factory);
//		OrderDAO orderDao = new OrderDaoImpl(factory);
//		UnapprovedAuthorDAO unapprovedAuthor = new UnapprovedAuthorDaoImpl(factory);
//		
//		User user = userDao.getUser("a");
//		
//		user.setFirstName("Arron");
//		userDao.updateUser(user);

//        factory.close();
    }
}
