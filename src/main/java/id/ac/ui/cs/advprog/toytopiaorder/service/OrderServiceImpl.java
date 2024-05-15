package id.ac.ui.cs.advprog.toytopiaorder.service;

import id.ac.ui.cs.advprog.toytopiaorder.model.CartItem;
import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void verifyOrder(String orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.verify();
            orderRepository.save(order);
        }
    }

    @Override
    public void cancelOrder(String orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.cancel();
            orderRepository.save(order);
        }
    }

    @Override
    public void setDeliveryMethod(String orderId, String deliveryMethod) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setDeliveryMethod(deliveryMethod);
            orderRepository.save(order);
        }
    }

    @Override
    public void completeOrder(String orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.complete();
            orderRepository.save(order);
        }
    }

    @Override
    public List<Order> findAll() {
        Iterator<Order> orderIterator = (Iterator<Order>) orderRepository.findAll();
        List<Order> allOrder = new ArrayList<>();
        orderIterator.forEachRemaining(allOrder::add);
        return allOrder;
    }

    @Override
    public Order createOrderFromCart(Long totalPrice, String cartId, Map<String, Object> cart)  {
        List<CartItem> cartItems = new ArrayList<>();
        if (cart != null) {
            Order order = new Order((Long) cart.get("totalPrice"), cartId);
            for (Map.Entry<String, Object> entry : cart.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof Map) {
                    CartItem item = new CartItem(cartId, (Long) cart.get("productId"), (String) cart.get("name"), (Integer) cart.get("quantity"), (Long) cart.get("price"), order);
                    cartItems.add(item);
                }
            }
            order.addCartItem(cartItems);
            orderRepository.save(order);
            return order;
        }
        return null;
    }
}
