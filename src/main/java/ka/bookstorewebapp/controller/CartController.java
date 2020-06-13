package ka.bookstorewebapp.controller;

import ka.bookstorewebapp.utils.OrderComparator;
import ka.bookstorewebapp.daos.*;
import ka.bookstorewebapp.entities.Book;
import ka.bookstorewebapp.entities.User;
import ka.bookstorewebapp.shoppingcart.Cart;
import ka.bookstorewebapp.shoppingcart.CartItem;
import ka.bookstorewebapp.shoppingcart.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private CartDAO cartDao;

    @Autowired
    private CartItemDAO cartItemDao;

    @Autowired
    private OrderDAO orderDao;

    @Autowired
    private BookDAO bookDao;

    public CartController() {
    }

    public CartController(UserDAO userDao, CartDAO cartDao, CartItemDAO cartItemDao, OrderDAO orderDao, BookDAO bookDao) {
        this.userDao = userDao;
        this.cartDao = cartDao;
        this.cartItemDao = cartItemDao;
        this.orderDao = orderDao;
        this.bookDao = bookDao;
    }

    @RequestMapping("/viewCart")
    public String goToViewCart(Model model, Principal principal) {
        User user = userDao.getUser(principal.getName());

        Cart cart = user.getCart();

        List<CartItem> listOfCartItems = new ArrayList<CartItem>();

        if (user.getCart() == null) {
            cart = new Cart();
        }

        for (CartItem eachCartItem : cart.getCartItems()) {
            listOfCartItems.add(eachCartItem);
        }

        model.addAttribute("cart", cart);
        model.addAttribute("cartItems", listOfCartItems);
        return "ViewCart";
    }

    @RequestMapping("/updateQuantity")
    public String doUpdateCartItem(@RequestParam int cartItemId, @RequestParam int quantity, Model model, Principal principal) {

        CartItem cartItem = cartItemDao.getCartItem(cartItemId);
        Cart cart = cartItem.getCart();

        if (quantity == 0) {
            cartItemDao.removeCartItem(cartItemId);
        } else {

            cartItem.setQuantity(quantity);

            cartItemDao.updateCartItem(cartItem);

            Cart updatedCart = cartDao.updateCart(cart);
        }
        model.addAttribute("cart", cart);
        return "ViewCart";
    }

    @RequestMapping("/proceedCheckout")
    public String goToCheckout(@RequestParam int cartId, Model model, Principal principal) {

        Cart cart = cartDao.getCart(cartId);
        User user = userDao.getUser(principal.getName());
        double total = cart.getTotal();

        model.addAttribute("total", total);
        model.addAttribute("cart", cart);
        model.addAttribute("user", user);
        return "Checkout";
    }

    @RequestMapping("/placeOrder")
    public String doPlaceOrder(@RequestParam int cartId, Model model, Principal principal) {
        User user = userDao.getUser(principal.getName());
        Cart cart = cartDao.getCart(cartId);

        Order order = new Order(cart);
        order.setStatus("Order Placed");
        order.setUser(user);

        orderDao.addOrder(order);
        cartDao.unassignCart(cartId);

        for (CartItem cartItem : cart.getCartItems()) {
            Book book = cartItem.getBook();
            int currentSales = book.getSales();
            int newSales = book.getSales() + cartItem.getQuantity();
            book.setSales(newSales);
            bookDao.updateBook(book);
        }

        return "OrderConfirmation";
    }

    @RequestMapping("/viewOrders")
    public String goToViewOrders(Model model, Principal principal) {

        User user = userDao.getUser(principal.getName());

        List<Order> listOfOrders = orderDao.getAllOrdersForUser(user);
        listOfOrders.sort(new OrderComparator());

        model.addAttribute("orders", listOfOrders);

        return "ViewOrders";
    }

}
