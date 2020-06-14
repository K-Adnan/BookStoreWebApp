package ka.bookstorewebapp.controller;

import ka.bookstorewebapp.daos.AdminDAO;
import ka.bookstorewebapp.daos.AuthorDAO;
import ka.bookstorewebapp.daos.UnapprovedAuthorDAO;
import ka.bookstorewebapp.daos.UserDAO;
import ka.bookstorewebapp.entities.Admin;
import ka.bookstorewebapp.entities.Author;
import ka.bookstorewebapp.entities.UnapprovedAuthor;
import ka.bookstorewebapp.entities.User;
import ka.bookstorewebapp.exceptions.RecordAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static ka.bookstorewebapp.utils.Logging.warn;

@Controller
public class RegistrationController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private AdminDAO adminDao;

    @Autowired
    private AuthorDAO authorDao;

    @Autowired
    private UnapprovedAuthorDAO unapprovedAuthorDao;

    @RequestMapping("/registerUser")
    public String goToRegisterUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "RegisterUser";
    }

    public RegistrationController() {
    }

    public RegistrationController(UserDAO userDao, AdminDAO adminDao, AuthorDAO authorDao, UnapprovedAuthorDAO unapprovedAuthorDao) {
        this.userDao = userDao;
        this.adminDao = adminDao;
        this.authorDao = authorDao;
        this.unapprovedAuthorDao = unapprovedAuthorDao;
    }

    @RequestMapping("/registerAuthor")
    public String goToRegisterAuthor(Model model) {
        UnapprovedAuthor ua = new UnapprovedAuthor();
        model.addAttribute("ua", ua);
        return "RegisterAuthor";
    }

    @RequestMapping("/registerAdmin")
    public String goToRegisterAdmin(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "/admin/RegisterAdmin";
    }

    @RequestMapping("/doRegisterUser")
    public String doRegisterUser(User user, Model model) {
        try {
            userDao.addUser(user);
        } catch(RecordAlreadyExistsException r) {
            warn("Attempted to register user with already existing email address: " + user, getClass());
            r.printStackTrace();
        }
        model.addAttribute("message", "Signup successful, you can sign in now");
        return "index";
    }

    @RequestMapping("/doRegisterAuthor")
    public String doRegisterAuthor(Author author, Model model) {
        author.setEmailAddress();
        authorDao.addAuthor(author);
        model.addAttribute("message", "Signup successful, you can sign in now");
        return "index";
    }

    @RequestMapping("/doRegisterAdmin")
    public String doRegisterAdmin(Admin admin, Model model) {
        admin.setEmailAddress();
        adminDao.addAdmin(admin);
        model.addAttribute("message", "Signup successful, you can sign in now");
        return "index";
    }

    @RequestMapping("/doRegisterUa")
    public String doRegisterUa(UnapprovedAuthor ua, Model model) {
        ua.generateEmailAddress();

        unapprovedAuthorDao.addUnapprovedAuthor(ua);
        model.addAttribute("message", "Request has been submitted. Please wait for approval.");
        return "index";
    }

}
