package id.ac.ui.cs.advprog.toytopiaorder.service;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.model.Cart;

import java.util.List;

public interface OrderService {
    void verifyOrder(String id);
    void cancelOrder(String id);
    void setDeliveryMethod(String id, String deliveryMethod);
    void completeOrder(String id);
    Order findOrderById(String id);
    List<Order> findAll();
    Order createOrderFromCart(Cart cart);
}
