package ka.bookstorewebapp.controller;

import ka.bookstorewebapp.daos.AuthorDAO;
import ka.bookstorewebapp.daos.RejectedUserDAO;
import ka.bookstorewebapp.daos.UnapprovedAuthorDAO;
import ka.bookstorewebapp.daos.UserDAO;
import ka.bookstorewebapp.entities.Author;
import ka.bookstorewebapp.entities.RejectedUser;
import ka.bookstorewebapp.entities.UnapprovedAuthor;
import ka.bookstorewebapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDao;


    @Autowired
    private UnapprovedAuthorDAO unapprovedAuthorDao;

    @Autowired
    private RejectedUserDAO rejectedUserDao;

    @Autowired
    private AuthorDAO authorDao;

    @RequestMapping("/admin/viewUser")

    public String goToViewUser() {
        return "admin/ViewUser";
    }

    public UserController() {
    }

    public UserController(UserDAO userDao, UnapprovedAuthorDAO unapprovedAuthorDao, RejectedUserDAO rejectedUserDao, AuthorDAO authorDao) {
        this.userDao = userDao;
        this.unapprovedAuthorDao = unapprovedAuthorDao;
        this.rejectedUserDao = rejectedUserDao;
        this.authorDao = authorDao;
    }

    @RequestMapping("/admin/displayUser")
    public String goToDisplayUser(@RequestParam String emailAddress, Model model) {
        User user = userDao.getUser(emailAddress);
        model.addAttribute("user", user);
        model.addAttribute("message", "User details for user with email address: " + emailAddress);
        return "admin/DisplayUser";
    }

    @RequestMapping("/admin/editUser")
    public String goToEditUser(Model model, @RequestParam String emailAddress) {
        User user = userDao.getUser(emailAddress);
        model.addAttribute("user", user);
        return "admin/EditUser";
    }

    @RequestMapping("/editPersonalDetails")
    public String goToEditPersonalDetails(Model model, Principal principal) {
        String emailAddress = principal.getName();
        User user = userDao.getUser(emailAddress);
        model.addAttribute("user", user);
        return "EditPersonalDetails";
    }

    @RequestMapping("/updatePersonalDetails")
    public String doUpdatePersonalDetails(User user, Principal principal, Model model) {
        String emailAddress = principal.getName();
        User oldUser = userDao.getUser(emailAddress);
        System.out.println(oldUser);

        oldUser.toBuilder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();

        userDao.updateUser(oldUser);
        model.addAttribute("message", "User details have been successfully updated");
        model.addAttribute("user", oldUser);
        return "ViewPersonalDetails";
    }

    @RequestMapping("/admin/UpdateProfile")
    public String doUpdateProfile(User user, Model model) {
        User oldUser = userDao.getUser(user.getEmailAddress());

        oldUser.toBuilder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();
        ;

        model.addAttribute("message", "User details have been successfully updated");
        userDao.updateUser(oldUser);
        model.addAttribute("user", oldUser);
        return "admin/DisplayUser";
    }

    @RequestMapping("/changePassword")
    public String goToChangePassword() {
        return "ChangePassword";
    }

    @RequestMapping("/updateNewPassword")
    public String doChangePassword(@RequestParam String currentPassword, @RequestParam String password, @RequestParam String confirmPassword, Principal principal, Model model) {
        String emailAddress = principal.getName();
        User user = userDao.getUser(emailAddress);

        if (currentPassword.equals(user.getPassword())) {
            if (password.equals(confirmPassword)) {
                user.setPassword(password);
                userDao.updateUser(user);
                model.addAttribute("user", user);
                model.addAttribute("message", "Password successfully updated");
                return "ViewPersonalDetails";
            } else {
                model.addAttribute("message", "Password and Confirm Password fields do not match");
                return "ChangePassword";
            }
        } else {
            model.addAttribute("message", "Current password is incorrect");
            return "ChangePassword";
        }
    }

    @RequestMapping("/admin/approveRequest")
    public String doApproveRequest(Model model, String emailAddress) {
        UnapprovedAuthor ua = unapprovedAuthorDao.getUnapprovedAuthor(emailAddress);

        Author author = Author.builder()
                .emailAddress(emailAddress)
                .firstName(ua.getFirstName())
                .lastName(ua.getLastName())
                .address(ua.getAddress())
                .password(ua.getPassword())
                .build();

        unapprovedAuthorDao.removeUnapprovedAuthor(emailAddress);
        authorDao.addAuthor(author);
        List<UnapprovedAuthor> list = unapprovedAuthorDao.getAllUnapprovedAuthors();
        model.addAttribute("list", list);
        return "admin/ViewAuthorRequests";
    }

    @RequestMapping("/admin/rejectRequest")
    public String doRejectRequest(Model model, String emailAddress, HttpServletRequest request) {
        UnapprovedAuthor ua = unapprovedAuthorDao.getUnapprovedAuthor(emailAddress);

        RejectedUser rj = new RejectedUser().builder()
                .emailAddress(ua.getEmailAddress())
                .firstName(ua.getFirstName())
                .lastName(ua.getLastName())
                .password(ua.getPassword())
                .address(ua.getAddress())
                .reasonForRejection(request.getParameter("reason"))
                .build();

        unapprovedAuthorDao.removeUnapprovedAuthor(emailAddress);

        rejectedUserDao.addRejectedUser(rj);

        List<UnapprovedAuthor> list = unapprovedAuthorDao.getAllUnapprovedAuthors();
        model.addAttribute("list", list);

        return "admin/ViewAuthorRequests";
    }

    @RequestMapping("/admin/viewRejectedAuthors")
    public String goToViewRejectedAuthors(Model model) {

        List<RejectedUser> listOfRejectedAuthors = rejectedUserDao.getAllRejectedUsers();

        model.addAttribute("rejectedAuthors", listOfRejectedAuthors);

        return "admin/ViewRejectedAuthors";
    }

}
