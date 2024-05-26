package id.ac.ui.cs.advprog.toytopiaorder.service;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Order verifyOrder(String id);
    Order cancelOrder(String id);
    Order setDeliveryMethod(String id, String deliveryMethod);
    Order completeOrder(String id);
    Order findByOrderId(String id);
    List<List<String>> findAll();
    List<List<String>> findByUserEmail(String userEmail);
    Order createOrderFromCart(String email, Double totalPrice, String cartId, Map<String, Map<String, Object>> cart);
}
