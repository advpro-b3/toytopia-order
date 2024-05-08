package id.ac.ui.cs.advprog.toytopiaorder.model;

import id.ac.ui.cs.advprog.toytopiaorder.enums.DeliveryMethod;
import id.ac.ui.cs.advprog.toytopiaorder.model.Cart;
import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;
    private Cart cart;

    @BeforeEach
    public void setUp() {
        cart = new Cart();
        order = new Order(cart, "1");
    }

    @Nested
    class InitialOrderTests {
        @Test
        public void testOrderStatusIsWaitingVerificationOnInitiation() {
            assertTrue(order.getState() instanceof WaitingVerificationState);
        }
    }

    @Nested
    class VerifyTests {
        @Test
        public void testVerify() {
            order.verify();
            assertTrue(order.getState() instanceof SetDeliveryState);
        }
    }

    @Nested
    class SetDeliveryMethodTests {
        @Test
        public void testSetDeliveryMethodInvalid() {
            order.setState(new SetDeliveryState(order));
            assertThrows(IllegalArgumentException.class, () -> order.setDeliveryMethod("InvalidMethod"));
        }

        @Test
        public void testSetDeliveryMethodIllegalState() {
            assertThrows(IllegalStateException.class, () -> order.setDeliveryMethod("JTE"));
        }
        @Test
        public void testSetDeliveryMethod() {
            order.setState(new SetDeliveryState(order));
            order.setDeliveryMethod(DeliveryMethod.JTE.getValue());
            assertEquals(DeliveryMethod.JTE.getValue(), order.getDeliveryMethod());
            assertTrue(order.getResiCode().startsWith("JTE-"));
            assertTrue(order.getState() instanceof InDeliveryState);
        }
    }

    @Nested
    class CompleteTests {
        @Test
        public void testCompleteIllegalStateException() {
            order.setState(new InDeliveryState(order));
            order.complete();
            assertTrue(order.getState() instanceof CompletedState);
        }
    }

    @Nested
    class CancelTests {
        @Test
        public void testCancel() {
            order.setState(new WaitingVerificationState(order));
            order.cancel();
            assertTrue(order.getState() instanceof CanceledState);
        }
    }
}
