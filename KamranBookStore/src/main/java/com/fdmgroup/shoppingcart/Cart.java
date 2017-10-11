package com.fdmgroup.shoppingcart;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fdmgroup.entities.User;

@Entity
@Table(name="TEST_CART")
public class Cart {
	
	@Id
	@GeneratedValue
	private int cartId;
	@OneToMany(mappedBy="cart")
	private Set<CartItem> cartItems = new HashSet<CartItem>();
	private double total;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="EMAILADDRESS")
	private User user;
	
	public Cart(){
	}
	
	public Set getCartItems(){
		return cartItems;
	}
	
	private void calculateTotal(){
		total = 0.0;
		for (CartItem cartItem : cartItems){
			double eachPrice = cartItem.getBook().getPrice();
			int quantity = cartItem.getQuantity();
			total += eachPrice * quantity;
		}
	}
	
	public double getTotal(){
		DecimalFormat f = new DecimalFormat("##.00");
		return Double.parseDouble(f.format(total));
	}
	
	public void addCartItem(CartItem cartItem){
		cartItems.add(cartItem);
		calculateTotal();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
