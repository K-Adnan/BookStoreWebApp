package com.fdmgroup.controller;


import com.fdmgroup.daos.BookDAO;
import com.fdmgroup.daos.UnapprovedAuthorDAO;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.entities.Book;
import com.fdmgroup.entities.UnapprovedAuthor;
import com.fdmgroup.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private BookDAO bookDao;

    @Autowired
    private UnapprovedAuthorDAO unapprovedAuthorDao;

    public WelcomeController() {
    }

    public WelcomeController(UserDAO userDao, BookDAO bookDao,
                             UnapprovedAuthorDAO unapprovedAuthorDao) {
        super();
        this.userDao = userDao;
        this.bookDao = bookDao;
        this.unapprovedAuthorDao = unapprovedAuthorDao;
    }

    @RequestMapping("/")
    public String goToIndex() {
        return "index";
    }

    @RequestMapping("/home")
    public String goToHome(String emailAddress, HttpSession session, Principal principal, HttpServletRequest request) {
        session.setAttribute("emailAddress", principal.getName());
        if (request.isUserInRole("User")) {
            return "redirect:user/userHome";
        } else if (request.isUserInRole("Admin")) {
            session.setAttribute("adminOptions", "<li><a href=\"\">Admin</a><ul><li><a href=\"/KamranBookStore/admin/viewUser\">View User</a></li><li><a href=\"/KamranBookStore/admin/viewAllSales\">View All Sales</a></li><li><a href=\"/KamranBookStore/author/listBook\">List New Book</a></li><li><a href=\"/KamranBookStore/admin/viewAuthorRequests\">View Author Requests</a></li></ul></li>");
            return "redirect:admin/adminHome";
        } else if (request.isUserInRole("Author")) {
            session.setAttribute("authorOptions", "<li><a href=\"/KamranBookStore/author/listBook\">List New Book</a></li><li><a href=\"/KamranBookStore/author/viewSales\">View Sales</a></li>");
            return "redirect:author/authorHome";
        }
        return "index";
    }

    @RequestMapping("/admin/adminHome")
    public String goToAdminHome(Model model, HttpSession session) {
        List<Book> booksList = bookDao.getAllBooks();
        Collections.sort(booksList);
        model.addAttribute("booksList", booksList);
        return "admin/AdminHome";
    }

    @RequestMapping("/user/userHome")
    public String goToUserHome(Model model, HttpSession session) {
        List<Book> booksList = bookDao.getAllBooks();
        Collections.sort(booksList);
        model.addAttribute("booksList", booksList);
        return "user/UserHome";
    }

    @RequestMapping("/author/authorHome")
    public String goToAuthorHome(Model model, HttpSession session) {
        List<Book> booksList = bookDao.getAllBooks();
        Collections.sort(booksList);
        model.addAttribute("booksList", booksList);
        return "author/AuthorHome";
    }

    @RequestMapping("/logout")
    public String doLogOut(Model model, HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/register")
    public String goToRegister(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "Register";
    }

    @RequestMapping("/searchBook")
    public String goToSearchBook(Model model) {
        List<String> categories = new ArrayList<String>();
        categories.add("Technology");
        categories.add("Biography");
        categories.add("Travel");
        categories.add("Fiction");

        model.addAttribute("categories", categories);
        return "SearchBook";
    }

    @RequestMapping("/viewPersonalDetails")
    public String goToViewPersonalDetails(Model model, Principal principal) {
        String emailAddress = principal.getName();

        User user = userDao.getUser(emailAddress);

        model.addAttribute("user", user);

        return "ViewPersonalDetails";
    }

    @RequestMapping("/admin/viewAuthorRequests")
    public String goToViewAuthorRequests(Model model) {
        List<UnapprovedAuthor> list = unapprovedAuthorDao.getAllUnapprovedAuthors();

        model.addAttribute("list", list);

        return "admin/ViewAuthorRequests";
    }

    @RequestMapping("/admin/viewAllSales")
    public String goToViewAllSales(Model model) {

        List<Book> booksList = bookDao.getAllBooks();
        Collections.sort(booksList);
        model.addAttribute("booksList", booksList);

        return "admin/ViewAllSales";
    }

    @RequestMapping("/help")
    public String goToHelp() {

        return "Help";
    }

    @RequestMapping("/contact")
    public String goToContact() {
        return "Contact";
    }

}
