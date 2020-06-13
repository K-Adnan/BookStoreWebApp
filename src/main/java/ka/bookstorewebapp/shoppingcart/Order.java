package ka.bookstorewebapp.shoppingcart;

import ka.bookstorewebapp.entities.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    private int orderId;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToOne(cascade = CascadeType.MERGE)
    private Cart cart;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    public Order() {
    }

    public Order(Cart cart) {
        this.setCart(cart);
        this.orderDate = new Date();
    }

    public String getStatus() {
        return status;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date date) {
        this.orderDate = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


}
