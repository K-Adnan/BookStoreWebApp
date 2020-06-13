package com.fdmgroup.controller;

import com.fdmgroup.daos.*;
import com.fdmgroup.entities.Author;
import com.fdmgroup.entities.Book;
import com.fdmgroup.entities.User;
import com.fdmgroup.exceptions.EntryAlreadyExistsException;
import com.fdmgroup.exceptions.NoSuchEntryException;
import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@Controller
public class BookController {

    @Autowired
    private EntityManagerFactory factory;

    @Autowired
    private CartItemDAO cartItemDao;

    @Autowired
    private CartDAO cartDao;

    @Autowired
    private BookDAO bookDao;

    @Autowired
    private AuthorDAO authorDao;

    @Autowired
    private UserDAO userDao;

    public BookController() {
    }

    public BookController(BookDAO bookDao, AuthorDAO authorDao, UserDAO userDao, CartDAO cartDao, CartItemDAO cartItemDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.userDao = userDao;
        this.cartDao = cartDao;
        this.cartItemDao = cartItemDao;
    }

    @RequestMapping("/viewAllBooks")
    public String goToViewAllBooks(Model model) {

        List<Book> booksList = bookDao.getAllBooks();
        Collections.sort(booksList);
        model.addAttribute("booksList", booksList);
        return "ViewAllBooks";
    }

    @RequestMapping("/displayBooks")
    public String goToDisplayBooks(@RequestParam Long isbn, @RequestParam String title, @RequestParam String author, @RequestParam String category, @RequestParam Double min, @RequestParam Double max, Model model) {

        List<Book> booksList;
        if (isbn != null) {
            Book book = null;
            try {
                book = bookDao.getBook(isbn);
            } catch (NoSuchEntryException e) {
                e.printStackTrace();
            }
            booksList = new ArrayList<Book>();
            booksList.add(book);
        } else {
            booksList = bookDao.getBooksByAllAttributes(title, author, category, min, max);
        }
        Collections.sort(booksList);
        List<Book> uniqueBooksList = new ArrayList<Book>(new LinkedHashSet<Book>(booksList));
        model.addAttribute("booksList", uniqueBooksList);
        return "ViewBooks";
    }

    @RequestMapping("/displayBook")
    public String goToDisplayBook(@RequestParam Long isbn, Model model, Principal principal, HttpServletRequest req) {

        Book book = null;
        try {
            book = bookDao.getBook(isbn);
        } catch (NoSuchEntryException e) {
            e.printStackTrace();
        }

        model.addAttribute("book", book);

        Set<Author> listOfAuthors = book.getAuthors();

        for (Author author : listOfAuthors) {
            if (author.getEmailAddress().equals(principal.getName())) {
                model.addAttribute("editMessage", "<a href='author/editBook?isbn=" + book.getIsbn() + "'> Edit Book </a>");
            }
        }

        return "ViewBook";
    }

    @RequestMapping("/viewBooksByCategory")
    public String goToViewBooksByCategory(@RequestParam String category, Model model) {

        List<Book> booksList = bookDao.getBooksByCategory(category);
        Collections.sort(booksList);
        model.addAttribute("booksList", booksList);

        return "ViewBooks";
    }

    @RequestMapping("/addBookToBasket")
    public String doAddBookToBasket(@RequestParam Long isbn, @RequestParam Integer quantity, Model model, Principal principal, HttpSession session) {

        Book book = null;
        try {
            book = bookDao.getBook(isbn);
        } catch (NoSuchEntryException e) {
            e.printStackTrace();
        }
        User user = userDao.getUser(principal.getName());

        if (quantity < 1) {
            model.addAttribute("message", "Quantity must be 1 or more");
        } else {

            Cart cart = null;
            if (user.getCart() == null) {
                cart = new Cart();
                cartDao.addCart(cart);
                user.setCart(cart);
            } else {
                cart = user.getCart();
            }

            CartItem cartItem = new CartItem(book, quantity, cart);
            cartItemDao.addCartItem(cartItem);

            cart.addCartItem(cartItem);
            userDao.updateUser(user);

            model.addAttribute("message", "<h4>Successfully added to <a href='viewCart'>Shopping Cart</a><h4>");
        }
        model.addAttribute("book", book);
        return "ViewBook";
    }

    @RequestMapping("/rateBook")
    public String doRateBook(@RequestParam long isbn, Model model, HttpServletRequest request) {

        int rating = Integer.parseInt(request.getParameter("rating"));

        Book book = null;
        try {
            book = bookDao.getBook(isbn);
        } catch (NoSuchEntryException e) {
            e.printStackTrace();
        }
        book.addCustomerRating(rating);
        bookDao.updateBook(book);

        model.addAttribute("book", book);
        model.addAttribute("message", "Thank you, your rating has been successfully saved<br/>");
        return "ViewBook";
    }

    @RequestMapping("/author/listBook")
    public String goToListNewBook(Model model, Principal principal, HttpServletRequest req) {

        Book book = new Book();
        List<Author> authorsList = authorDao.getAllAuthors();
        List<String> authorEmails = new ArrayList<String>();

        for (Author author : authorsList) {
            authorEmails.add(author.getEmailAddress().split("@")[0]);
        }

        if (req.isUserInRole("Author")) {
            model.addAttribute("authorString", principal.getName().split("@")[0]);
        }

        model.addAttribute("book", book);
        model.addAttribute("authorsList", authorEmails);

        return "author/ListNewBook";
    }

    @RequestMapping("/author/doListBook")
    public String doListNewBook(@RequestParam String authorsString, Model model, Book book, HttpServletRequest request) {
        String[] authors = authorsString.split(",");

        for (String authorStr : authors) {
            String emailAddress = authorStr + "@books4u.com";
            Author author = authorDao.getAuthor(emailAddress);
            book.setAuthor(author);
        }

        try {
            bookDao.addBook(book);
        } catch (EntryAlreadyExistsException e) {
            e.printStackTrace();
        }

        model.addAttribute("message", "Book '" + book.getTitle() + "' has been successfully addd");

        return "admin/AdminHome";

    }

    @RequestMapping("/author/viewSales")
    public String goToViewSales(Model model, Principal principal) {

        List<Book> listOfBooks = bookDao.getBooksByAuthor(principal.getName());

        model.addAttribute("listOfBooks", listOfBooks);

        return "author/ViewSales";
    }

    @RequestMapping("/author/editBook")
    public String goToEditBook(@RequestParam long isbn, Model model, Principal principal, HttpServletRequest req) {

        Book book = null;
        try {
            book = bookDao.getBook(isbn);
        } catch (NoSuchEntryException e) {
            e.printStackTrace();
        }

        List<Author> authorsList = authorDao.getAllAuthors();
        List<String> authorEmails = new ArrayList<String>();

        for (Author author : authorsList) {
            authorEmails.add(author.getEmailAddress().split("@")[0]);
        }

        if (req.isUserInRole("Author")) {
            model.addAttribute("authorString", principal.getName().split("@")[0]);
        }

        model.addAttribute("book", book);
        model.addAttribute("authorsList", authorEmails);

        return "author/EditBook";
    }

    @RequestMapping("/author/doEditBook")
    public String doEditBook(Long isbn, @RequestParam String authorsString, Book book, Model model, Principal principal, HttpServletRequest req) {

        Book oldBook = null;
        try {
            oldBook = bookDao.getBook(isbn);
        } catch (NoSuchEntryException e) {
            e.printStackTrace();
        }

        oldBook.setTitle(book.getTitle());
        oldBook.setCategory(book.getCategory());
        oldBook.setNumberOfPages(book.getNumberOfPages());
        oldBook.setReleaseYear(book.getReleaseYear());
        oldBook.setImageUrl(book.getImageUrl());
        oldBook.setQuantity(book.getQuantity());
        bookDao.updateBook(oldBook);

        return "redirect:viewSales";
    }

    @RequestMapping("/viewBiographyBooks")
    public String goToViewBiographyBooks(Model model) {

        List<Book> booksList = bookDao.getBooksByCategory("Biography");
        Collections.sort(booksList);
        model.addAttribute("booksList", booksList);
        model.addAttribute("category", "Biography");

        return "ViewBooks";
    }

    @RequestMapping("/viewFictionBooks")
    public String goToViewFictionBooks(Model model) {

        List<Book> booksList = bookDao.getBooksByCategory("Fiction");
        Collections.sort(booksList);
        model.addAttribute("booksList", booksList);
        model.addAttribute("category", "Fiction");

        return "ViewBooks";
    }

    @RequestMapping("/viewTechnologyBooks")
    public String goToViewTechnologyBooks(Model model) {

        List<Book> booksList = bookDao.getBooksByCategory("Technology");
        Collections.sort(booksList);
        model.addAttribute("booksList", booksList);
        model.addAttribute("category", "Technology");

        return "ViewBooks";
    }

    @RequestMapping("/viewTravelBooks")
    public String goToViewTravelBooks(Model model) {

        List<Book> booksList = bookDao.getBooksByCategory("Travel");
        Collections.sort(booksList);
        model.addAttribute("booksList", booksList);
        model.addAttribute("category", "Travel");

        return "ViewBooks";
    }

    @RequestMapping("/viewBooks")
    public String goToViewTravelBooks(@RequestParam String searchitem, Model model) {

        List<Book> booksList = bookDao.getBooksByAllAttributes(searchitem);
        Collections.sort(booksList);
        List<Book> list = new ArrayList<Book>(new LinkedHashSet<Book>(booksList));
        model.addAttribute("booksList", list);

        return "ViewBooks";
    }

    @RequestMapping("/viewBooksByAuthor")
    public String goToViewBooksByAuthor(String author, Model model, Principal principal) {

        List<Book> listOfBooks = bookDao.getBooksByAuthor(author);
        Author theAuthor = authorDao.getAuthor(author);
        Collections.sort(listOfBooks);
        model.addAttribute("booksList", listOfBooks);
        model.addAttribute("category", theAuthor.getFirstName() + " " + theAuthor.getLastName());

        return "ViewBooks";
    }

}
