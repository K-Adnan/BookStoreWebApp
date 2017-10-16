package com.fdmgroup.comparators;

import java.util.Comparator;

import com.fdmgroup.entities.Book;
import com.fdmgroup.shoppingcart.Order;

public class OrderComparator implements Comparator<Order> {

	public int compare(Order o1, Order o2) {
		if (o1.getOrderDate().after(o2.getOrderDate())) {
			return -1;
		} else if (o1.getOrderDate().before(o2.getOrderDate())) {
			return 1;
		} else {
			return 0;
		}

	}
}