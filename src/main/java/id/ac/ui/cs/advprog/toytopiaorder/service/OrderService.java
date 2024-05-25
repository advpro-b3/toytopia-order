package id.ac.ui.cs.advprog.toytopiaorder.service;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void verifyOrder(String id);
    void cancelOrder(String id);
    void setDeliveryMethod(String id, String deliveryMethod);
    void completeOrder(String id);
    Order findByOrderId(String id);
    List<Order> findAll();
    Order createOrderFromCart(Double totalPrice, String cartId, Map<String, Map<String, Object>> cart);
}
