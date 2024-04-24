package id.ac.ui.cs.advprog.toytopiaorder.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private Order order;
    private Cart cart;

    @BeforeEach
    public void setUp() {
        cart = new Cart();
        order = new Order(cart);
    }

    @Test
    public void testOrderIdIsNotNull() {
        assertNotNull(order.getId());
    }

    @Test
    public void testOrderStatusIsWaitingVerificationOnInitiation() {
        assertEquals(OrderStatus.WAITING_VERIFICATION, order.getStatus());
    }

    @Test
    public void testSetDeliveryMethod() {
        order.setDeliveryMethod(DeliveryMethod.JTE);
        assertEquals(DeliveryMethod.JTE, order.getDeliveryMethod());
        assertTrue(order.getResiCode().startsWith("JTE-"));
    }

    @Test
    public void testSetDeliveryMethodInvalid() {
        assertThrows(IllegalArgumentException.class, () -> order.setDeliveryMethod("InvalidMethod"));
    }

    @Test
    public void testSetStatus() {
        order.setStatus(OrderStatus.IN_DELIVERY);
        assertEquals(OrderStatus.IN_DELIVERY, order.getStatus());
    }

    @Test
    public void testSetStatusInvalid() {
        assertThrows(IllegalArgumentException.class, () -> order.setStatus("InvalidStatus"));
    }
}
