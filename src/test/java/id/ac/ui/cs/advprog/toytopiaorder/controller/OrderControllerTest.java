package id.ac.ui.cs.advprog.toytopiaorder.controller;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.service.CartService;
import id.ac.ui.cs.advprog.toytopiaorder.service.OrderService;
import id.ac.ui.cs.advprog.toytopiaorder.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.*;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class OrderControllerTest {
    @Mock
    private OrderService orderService;

    @Mock
    private CartService cartService;

    @Mock
    private UserService userService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckoutSuccess() {
        // Arrange
        String cartId = "123";
        String token = "validToken";
        String email = "user@example.com";

        Map<String, Object> cart = new HashMap<>();
        cart.put("totalPrice", 100.0);
        cart.put("cartItems", new HashMap<>());

        Order order = new Order(email, 100.0);

        when(userService.getEmail(anyString())).thenReturn(CompletableFuture.completedFuture(email));
        when(cartService.getCartById(anyString())).thenReturn(cart);
        when(orderService.createOrderFromCart(anyString(), anyDouble(), anyString(), anyMap())).thenReturn(order);

        // Act
        CompletableFuture<ResponseEntity<String>> responseFuture = orderController.checkout(cartId, token);

        // Assert
        assertEquals(HttpStatus.OK, responseFuture.join().getStatusCode());
    }

    @Test
    void testVerifyOrder() {
        String token = "validToken";
        String orderId = "123";
        Order order = new Order();

        when(userService.isAdmin(anyString())).thenReturn(CompletableFuture.completedFuture(true));
        when(orderService.verifyOrder(anyString())).thenReturn(order);

        CompletableFuture<ResponseEntity<Order>> response = orderController.verifyOrder(token, orderId);

        assertEquals(HttpStatus.OK, response.join().getStatusCode());
        verify(userService, times(1)).isAdmin(anyString());
        verify(orderService, times(1)).verifyOrder(anyString());
    }

    @Test
    void testCancelOrder() {
        String token = "validToken";
        String orderId = "123";
        Order order = new Order();

        when(userService.isAdmin(anyString())).thenReturn(CompletableFuture.completedFuture(true));
        when(orderService.cancelOrder(anyString())).thenReturn(order);

        CompletableFuture<ResponseEntity<Order>> response = orderController.cancelOrder(token, orderId);

        assertEquals(HttpStatus.OK, response.join().getStatusCode());
        verify(userService, times(1)).isAdmin(anyString());
        verify(orderService, times(1)).cancelOrder(anyString());
    }

    @Test
    void testSetDeliveryMethod() {
        String token = "validToken";
        String orderId = "123";
        String deliveryMethod = "Express";
        Order order = new Order();

        when(userService.isAdmin(anyString())).thenReturn(CompletableFuture.completedFuture(true));
        when(orderService.setDeliveryMethod(anyString(), anyString())).thenReturn(order);

        CompletableFuture<ResponseEntity<Order>> response = orderController.verifyOrder(token, orderId, deliveryMethod);

        assertEquals(HttpStatus.OK, response.join().getStatusCode());
        verify(userService, times(1)).isAdmin(anyString());
        verify(orderService, times(1)).setDeliveryMethod(anyString(), anyString());
    }

    @Test
    void testCompleteOrder() {
        String orderId = "123";
        Order order = new Order();

        when(orderService.completeOrder(anyString())).thenReturn(order);

        ResponseEntity<Order> response = orderController.ccmpleteOrder(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(orderService, times(1)).completeOrder(anyString());
    }

    @Test
    void testOrderListPage() {
        String token = "validToken";
        String orderId = "123";
        String email = "user@example.com";
        List<List<String>> orders = Collections.emptyList();

        when(userService.getEmail(anyString())).thenReturn(CompletableFuture.completedFuture(email));
        when(orderService.findByUserEmail(anyString())).thenReturn(orders);

        CompletableFuture<ResponseEntity<List<List<String>>>> response = orderController.OrderListPage(token);

        assertEquals(HttpStatus.OK, response.join().getStatusCode());
        verify(userService, times(1)).getEmail(anyString());
        verify(orderService, times(1)).findByUserEmail(anyString());
    }

    @Test
    void testOrderListPageAdmin() {
        String token = "validToken";
        List<List<String>> orders = Collections.emptyList();

        when(userService.isAdmin(anyString())).thenReturn(CompletableFuture.completedFuture(true));
        when(orderService.findAll()).thenReturn(orders);

        CompletableFuture<ResponseEntity<List<List<String>>>> response = orderController.OrderListPageAdmin(token);

        assertEquals(HttpStatus.OK, response.join().getStatusCode());
        verify(userService, times(1)).isAdmin(anyString());
        verify(orderService, times(1)).findAll();
    }
}
