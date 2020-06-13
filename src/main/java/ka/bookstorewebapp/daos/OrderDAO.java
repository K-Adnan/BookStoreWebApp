package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.entities.User;
import ka.bookstorewebapp.shoppingcart.Order;

import java.util.List;

public interface OrderDAO {

    public void addOrder(Order order);

    public void updateOrder(Order order);

    public Order getOrder(int orderId);

    public void removeOrder(int orderId);

    public List<Order> getAllOrdersForUser(User user);

}
