package com.fdmgroup.shoppingcart;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fdmgroup.entities.Book;

@Entity
@Table(name="TEST_CARTITEMS")
public class CartItem {
	
	@Id
	@GeneratedValue
	private int cartItemId;
	private long isbn;
	@ManyToOne(fetch=FetchType.EAGER)
	private Cart cart;
	@ManyToOne(fetch=FetchType.EAGER)
	private Book book;
	private int quantity;
	
	public CartItem(){
	}
	
	public CartItem(Book book, int quantity, Cart cart) {
		this.isbn = book.getIsbn();
		this.book = book;
		this.quantity = quantity;
		this.cart = cart;
	}

	public long getIsbn() {
		return isbn;
	}

	public Book getBook() {
		return book;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

}
