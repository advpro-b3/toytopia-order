package id.ac.ui.cs.advprog.toytopiaorder.model;

import id.ac.ui.cs.advprog.toytopiaorder.model.state.InDeliveryState;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.WaitingVerificationState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import id.ac.ui.cs.advprog.toytopiaorder.enums.DeliveryMethod;

@Nested
class OrderTest {

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
        assertTrue(order.getState() instanceof WaitingVerificationState);
    }

    @Test
    public void testSetDeliveryMethod() {
        order.setDeliveryMethod(DeliveryMethod.JTE.getValue());
        assertEquals(DeliveryMethod.JTE.getValue(), order.getDeliveryMethod());
        assertTrue(order.getResiCode().startsWith("JTE-"));
    }

    @Test
    public void testSetDeliveryMethodInvalid() {
        assertThrows(IllegalArgumentException.class, () -> order.setDeliveryMethod("InvalidMethod"));
    }

    @Test
    public void testSetStatus() {
        order.setStatus("IN_DELIVERY");
        assertEquals("IN_DELIVERY", order.getStatus());
        // Ensure state changes accordingly
        assertTrue(order.getState() instanceof InDeliveryState);
}

    @Test
    public void testSetStatusInvalid() {
        assertThrows(IllegalArgumentException.class, () -> order.setStatus("InvalidStatus"));
    }
}
