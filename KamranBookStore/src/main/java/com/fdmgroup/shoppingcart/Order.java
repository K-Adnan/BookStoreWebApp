package com.fdmgroup.shoppingcart;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fdmgroup.entities.User;

@Entity
@Table(name="TEST_ORDERS")
public class Order {
	
	@Id
	private int orderId;
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	@OneToOne(cascade=CascadeType.ALL)
	private Cart cart;
	private String status;
	private Timestamp orderDate;
	
	public Order(){
	}
	
	public Order(Cart cart){
		this.setCart(cart);
		this.orderDate = new Timestamp(new Date().getTime());
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

	public SimpleDateFormat getDf() {
		return df;
	}

	public void setDf(SimpleDateFormat df) {
		this.df = df;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp date) {
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
