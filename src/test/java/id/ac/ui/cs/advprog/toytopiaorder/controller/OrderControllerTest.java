package id.ac.ui.cs.advprog.toytopiaorder.controller;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.service.CartService;
import id.ac.ui.cs.advprog.toytopiaorder.service.OrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private CartService cartService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCheckoutSuccess() {
        String cartId = "cart123";
        Map<String, Object> cart = new HashMap<>();
        cart.put("totalPrice", 100.0);
        Map<String, Map<String, Object>> cartItems = new HashMap<>();
        cart.put("cartItems", cartItems);
        when(cartService.getCartById(cartId)).thenReturn(cart);

        Order order = new Order();
        when(orderService.createOrderFromCart(100.0, cartId, cartItems)).thenReturn(order);

        ResponseEntity<String> response = orderController.checkout(cartId);

        assertEquals("Order created successfully with ID: " + order.getOrderId(), response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testCheckoutFailure() {
        String cartId = "cart123";
        when(cartService.getCartById(cartId)).thenReturn(null);

        ResponseEntity<String> response = orderController.checkout(cartId);

        assertEquals("Cart with ID cart123 not found.", response.getBody());
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void testVerifyOrder() {
        String orderId = "order123";

        orderController.verifyOrder(orderId);

        verify(orderService, times(1)).verifyOrder(orderId);
    }

    @Test
    void testCancelOrder() {
        String orderId = "order123";

        orderController.cancelOrder(orderId);

        verify(orderService, times(1)).cancelOrder(orderId);
    }

    @Test
    void testSetDeliveryMethod() {
        String orderId = "order123";
        String deliveryMethod = "standard";

        orderController.setDeliveryMethod(orderId, deliveryMethod);

        verify(orderService, times(1)).setDeliveryMethod(orderId, deliveryMethod);
    }

    @Test
    void testCompleteOrder() {
        String orderId = "order123";

        orderController.completeOrder(orderId);

        verify(orderService, times(1)).completeOrder(orderId);
    }
}