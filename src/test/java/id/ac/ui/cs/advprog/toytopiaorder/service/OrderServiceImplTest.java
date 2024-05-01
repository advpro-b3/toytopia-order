package id.ac.ui.cs.advprog.toytopiaorder.service;

import id.ac.ui.cs.advprog.toytopiaorder.model.Cart;
import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.InDeliveryState;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.SetDeliveryState;
import id.ac.ui.cs.advprog.toytopiaorder.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartService cartService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testVerifyOrder() {
        Order order = new Order(new Cart());
        when(orderRepository.findById("1")).thenReturn(order);
        orderService.verifyOrder("1");
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testCancelOrder() {
        Order order = new Order(new Cart());
        when(orderRepository.findById("1")).thenReturn(order);
        orderService.cancelOrder("1");
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testSetDeliveryMethod() {
        Order order = new Order(new Cart());
        order.setState(new SetDeliveryState(order));
        when(orderRepository.findById("1")).thenReturn(order);
        orderService.setDeliveryMethod("1", "JTE");
        assertEquals("JTE", order.getDeliveryMethod());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testCompleteOrder() {
        Order order = new Order(new Cart());
        when(orderRepository.findById("1")).thenReturn(order);
        order.setState(new InDeliveryState(order));
        orderService.completeOrder("1");
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testFindOrderById() {
        Order order = new Order(new Cart());
        when(orderRepository.findById("1")).thenReturn(order);
        assertEquals(order, orderService.findOrderById("1"));
    }

    @Test
    void testFindAll() {
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order(new Cart());
        Order order2 = new Order(new Cart());
        orders.add(order1);
        orders.add(order2);
        when(orderRepository.findAll()).thenReturn(orders.iterator());
        assertEquals(orders, orderService.findAll());
    }

    @Test
    void testCreateOrderFromCart() {
        Cart cart = new Cart();
        cart.setId("1");
        Order created = orderService.createOrderFromCart(cart);
        when(cartService.getCartById("1")).thenReturn(cart);
        assertEquals("1", created.getId());
    }
}
