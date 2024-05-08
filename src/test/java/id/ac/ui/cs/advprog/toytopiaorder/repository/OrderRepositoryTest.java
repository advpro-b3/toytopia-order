package id.ac.ui.cs.advprog.toytopiaorder.repository;

import id.ac.ui.cs.advprog.toytopiaorder.enums.DeliveryMethod;
import id.ac.ui.cs.advprog.toytopiaorder.model.Cart;
import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class OrderRepositoryTest {

    private OrderRepository orderRepository;
    private Cart cart1;
    private Cart cart2;
    private Order order1;
    private Order order2;

    @BeforeEach
    public void setUp() {
        orderRepository = new OrderRepository();
        cart1 = new Cart();
        cart1.setId("1");
        cart2 = new Cart();
        cart2.setId("2");
        order1 = new Order(cart1, "1");
        order2 = new Order(cart2, "2");
    }

    @Test
    void testSaveandFind() {
        orderRepository.save(order1);

        Iterator<Order> orderIterator = orderRepository.findAll();
        assertTrue(orderIterator.hasNext());
        Order savedOrder = orderIterator.next();
        assertEquals(order1.getId(), savedOrder.getId());
        assertEquals(order1.getState(), savedOrder.getState());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Order> orderIterator = orderRepository.findAll();
        assertFalse(orderIterator.hasNext());
    }

    @Test
    public void testFindOrderById() {
        orderRepository.save(order1);
        orderRepository.save(order2);

        Order foundOrder = orderRepository.findById(order1.getId());
        assertEquals(order1, foundOrder);
    }
}
