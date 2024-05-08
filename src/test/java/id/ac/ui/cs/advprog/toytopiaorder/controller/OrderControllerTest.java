package id.ac.ui.cs.advprog.toytopiaorder.controller;

import id.ac.ui.cs.advprog.toytopiaorder.model.Cart;
import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.service.CartService;
import id.ac.ui.cs.advprog.toytopiaorder.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private CartService cartService;

    @Mock
    private Model model;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCheckoutValidCart() {
        String cartId = "123";
        Cart cart = new Cart();
        Order order = new Order(cart, cartId);
        when(cartService.getCartById(cartId)).thenReturn(cart);
        when(orderService.createOrderFromCart(cart, cartId)).thenReturn(order);

        ResponseEntity<String> response = orderController.checkout(cartId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order created successfully with ID: " + order.getId(), response.getBody());
    }

    @Test
    void testCheckoutInvalidCart() {
        String cartId = "123";
        when(cartService.getCartById(cartId)).thenReturn(null);

        ResponseEntity<String> response = orderController.checkout(cartId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Cart with ID 123 not found.", response.getBody());
    }

    @Test
    void testVerifyOrder() {
        String orderId = "123";
        orderController.verifyOrder(orderId);
        verify(orderService, times(1)).verifyOrder(orderId);
    }

    @Test
    void testCancelOrder() {
        String orderId = "123";
        orderController.cancelOrder(orderId);
        verify(orderService, times(1)).cancelOrder(orderId);
    }

    @Test
    void testSetDeliveryMethod() {
        String orderId = "123";
        String deliveryMethod = "JTE";
        orderController.setDeliveryMethod(orderId, deliveryMethod);
        verify(orderService, times(1)).setDeliveryMethod(orderId, deliveryMethod);
    }

    @Test
    void testCompleteOrder() {
        String orderId = "123";
        orderController.completeOrder(orderId);
        verify(orderService, times(1)).completeOrder(orderId);
    }

    @Test
    void testOrderListPage() {
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order(new Cart(), "1");
        Order order2 = new Order(new Cart(), "2");
        orders.add(order1);
        orders.add(order2);
        when(orderService.findAll()).thenReturn(orders);

        String viewName = orderController.OrderListPage(model);
        assertEquals("BuyerOrderList", viewName);
    }
}

