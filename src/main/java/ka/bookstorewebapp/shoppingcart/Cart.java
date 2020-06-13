package ka.bookstorewebapp.shoppingcart;

import ka.bookstorewebapp.entities.User;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CART")
public class Cart {

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Id
    @GeneratedValue
    private int cartId;
    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems = new HashSet<CartItem>();
    private double total;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMAILADDRESS")
    private User user;

    public Cart() {
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void calculateTotal() {
        total = 0.0;
        for (CartItem cartItem : cartItems) {
            double eachPrice = cartItem.getBook().getPrice();
            int quantity = cartItem.getQuantity();
            total += eachPrice * quantity;
        }
    }

    public double getTotal() {
        total = 0.0;
        for (CartItem cartItem : cartItems) {
            double eachPrice = cartItem.getBook().getPrice();
            int quantity = cartItem.getQuantity();
            total += eachPrice * quantity;
        }
        DecimalFormat f = new DecimalFormat("##.00");
        return Double.parseDouble(f.format(total));
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        calculateTotal();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart [cartId=" + cartId + ", total=" + total + "]";
    }


}
