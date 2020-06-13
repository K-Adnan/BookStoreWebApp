package ka.bookstorewebapp.comparators;

import ka.bookstorewebapp.shoppingcart.Order;

import java.util.Comparator;

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