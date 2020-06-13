package com.fdmgroup.entities;

import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setUserEmail(String userEmail) {
        this.emailAddress = userEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "[EmailAddress=" + emailAddress + ", FirstName=" + firstName + ", LastName=" + lastName + "]";
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        cart.setUser(this);
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}

