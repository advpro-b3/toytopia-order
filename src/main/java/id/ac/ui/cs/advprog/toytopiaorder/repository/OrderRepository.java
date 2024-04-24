package id.ac.ui.cs.advprog.toytopiaorder.repository;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

@Repository
public class OrderRepository {
    private List<Order> orderData = new ArrayList<>();
    public Order save(Order order) {
        int i = 0;
        for (Order savedOrder : orderData) {
            if (savedOrder.getId().equals(order.getId())) {
                orderData.remove(i);
                orderData.add(i, order);
                return order;
            }
            i += 1;
        }
        orderData.add(order);
        return order;
    }

    public Iterator<Order> findAll() {
        return orderData.iterator();
    }
    public Order findById(String id) {
        for (Order savedOrder : orderData) {
            if (savedOrder.getId().equals(id)) {
                return savedOrder;
            }
        }
        return null;
    }
}