package ka.bookstorewebapp.shoppingcart;

import ka.bookstorewebapp.entities.User;
import lombok.Data;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CART")
@Data
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

}
