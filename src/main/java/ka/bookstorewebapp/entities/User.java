package ka.bookstorewebapp.entities;

import ka.bookstorewebapp.shoppingcart.Cart;
import ka.bookstorewebapp.shoppingcart.Order;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Data
public class User {

    @Id
    private String emailAddress;
    private String password;
    @Transient
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String address;

    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CARTID")
    private Cart cart;
    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new HashSet<Order>();

    public User() {
    }

    public User(String userEmail, String password, String firstName, String lastName, String address, String phoneNumber) {
        this.emailAddress = userEmail;
        this.setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        cart.setUser(this);
    }

}

