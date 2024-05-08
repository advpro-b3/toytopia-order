package id.ac.ui.cs.advprog.toytopiaorder.service;

import id.ac.ui.cs.advprog.toytopiaorder.model.Cart;
import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void verifyOrder(String orderId) {
        Order order = orderRepository.findById(orderId);
        order.verify();
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(String orderId) {
        Order order = orderRepository.findById(orderId);
        order.cancel();
        orderRepository.save(order);
    }

    @Override
    public void setDeliveryMethod(String orderId, String deliveryMethod) {
        Order order = orderRepository.findById(orderId);
        order.setDeliveryMethod(deliveryMethod);
        orderRepository.save(order);
    }

    @Override
    public void completeOrder(String orderId) {
        Order order = orderRepository.findById(orderId);
        order.complete();
        orderRepository.save(order);
    }

    @Override
    public Order findOrderById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        Iterator<Order> orderIterator = orderRepository.findAll();
        List<Order> allOrder = new ArrayList<>();
        orderIterator.forEachRemaining(allOrder::add);
        return allOrder;
    }

    @Override
    public Order createOrderFromCart(Cart cart, String cartId) {
        if (cart != null) {
            Order order = new Order(cart, cartId);
            orderRepository.save(order);
            return order;
        }
        return null;
    }
}
