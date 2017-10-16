package com.fdmgroup.shoppingcart;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fdmgroup.entities.User;

@Entity
@Table(name="TEST_ORDERS")
public class Order {
	
	@Id
	@GeneratedValue
	private int orderId;
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	@OneToOne(cascade=CascadeType.MERGE)
	private Cart cart;
	private String status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
	public Order(){
	}
	
	public Order(Cart cart){
		this.setCart(cart);
		this.orderDate = new Date();
	}
	
	public String getStatus(){
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
