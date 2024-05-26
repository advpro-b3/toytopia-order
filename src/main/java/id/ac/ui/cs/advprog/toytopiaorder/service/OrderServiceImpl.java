package id.ac.ui.cs.advprog.toytopiaorder.service;

import id.ac.ui.cs.advprog.toytopiaorder.model.CartItem;
import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.*;
import id.ac.ui.cs.advprog.toytopiaorder.repository.OrderRepository;
import id.ac.ui.cs.advprog.toytopiaorder.repository.StateRepository;
import id.ac.ui.cs.advprog.toytopiaorder.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WaitingVerificationStateRepository waitingVerificationStateRepository;
    private final SetDeliveryStateRepository setDeliveryStateRepository;
    private final InDeliveryStateRepository inDeliveryStateRepository;
    private final CompletedStateRepository completedStateRepository;
    private final CanceledStateRepository canceledStateRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(OrderRepository orderRepository, WaitingVerificationStateRepository waitingVerificationStateRepository, InDeliveryStateRepository inDeliveryStateRepository, SetDeliveryStateRepository setDeliveryStateRepository, CompletedStateRepository completedStateRepository, CanceledStateRepository canceledStateRepository) {
        this.orderRepository = orderRepository;
        this.waitingVerificationStateRepository = waitingVerificationStateRepository;
        this.inDeliveryStateRepository = inDeliveryStateRepository;
        this.setDeliveryStateRepository = setDeliveryStateRepository;
        this.completedStateRepository = completedStateRepository;
        this.canceledStateRepository = canceledStateRepository;
    }

    @Override
    public Order verifyOrder(String orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        try {
            order.verify();
            SetDeliveryState setDeliveryState = new SetDeliveryState(order);
            setDeliveryStateRepository.save(setDeliveryState);
            order.setState(setDeliveryState);
            orderRepository.save(order);
        }
        catch (Exception e) {
            logger.error("Error occurred: " + e.getMessage());
        }
        return order;
    }

    @Override
    public Order cancelOrder(String orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        try {
            order.cancel();
            CanceledState canceledState = new CanceledState(order);
            canceledStateRepository.save(canceledState);
            order.setState(canceledState);
            orderRepository.save(order);
        }
        catch (Exception e) {
            logger.error("Error occurred: " + e.getMessage());
        }
        return order;
    }

    @Override
    public Order setDeliveryMethod(String orderId, String deliveryMethod) {
        Order order = orderRepository.findByOrderId(orderId);
        try {
            order.setDeliveryMethod(deliveryMethod);
            InDeliveryState inDeliveryState = new InDeliveryState(order);
            inDeliveryStateRepository.save(inDeliveryState);
            order.setState(inDeliveryState);
            orderRepository.save(order);
        }
        catch (Exception e) {
            logger.error("Error occurred: " + e.getMessage());
        }
        return order;
    }

    @Override
    public Order completeOrder(String orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        try {
            order.complete();
            CompletedState completedState = new CompletedState(order);
            completedStateRepository.save(completedState);
            order.setState(completedState);
            orderRepository.save(order);
        }
        catch (Exception e) {
            logger.error("Error occurred: " + e.getMessage());
        }
        return order;
    }

    @Override
    public List<List<String>> findByUserEmail(String email) {
        return orderRepository.retrieveOrderByEmail(email);
    }

    @Override
    public List<List<String>> findAll() {
        return orderRepository.retrieveAllOrder();
    }

    @Override
    public Order findByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public Order createOrderFromCart(String email, Double totalPrice, String cartId, Map<String, Map<String, Object>> cart)  {
        WaitingVerificationState waitingVerificationState = new WaitingVerificationState(null);
        waitingVerificationStateRepository.save(waitingVerificationState);
        List<CartItem> cartItems = new ArrayList<>();
        if (cart != null) {
            Order order = new Order(email, totalPrice);
            for (Map.Entry<String, Map<String, Object>> entry : cart.entrySet()) {
                Map<String, Object> value = entry.getValue();
                if (value != null) {
                    CartItem item = new CartItem((String) value.get("productId"), (String) value.get("name"), (Integer) value.get("quantity"), (Double) value.get("price"), order);
                    cartItems.add(item);
                }
            }
            order.addCartItem(cartItems);
            orderRepository.save(order);
            waitingVerificationState.setOrder(order);
            order.setState(waitingVerificationState);
            orderRepository.save(order);
            return order;
        }
        return null;
    }
}
