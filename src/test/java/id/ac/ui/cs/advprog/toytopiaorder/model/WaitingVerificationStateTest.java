package id.ac.ui.cs.advprog.toytopiaorder.model;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.WaitingVerificationState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class WaitingVerificationStateTest {

    private WaitingVerificationState waitingVerificationState;
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order("abc123", 100.0);
        waitingVerificationState = new WaitingVerificationState(order);
    }

    @Test
    void testWaitingVerificationStateConstructor() {
        assertEquals(order, waitingVerificationState.getOrder());
        assertEquals("Waiting for Verification", waitingVerificationState.getStatus());
    }

    @Test
    void testSetOrder() {
        Order newOrder = new Order("abc124",200.0);
        waitingVerificationState.setOrder(newOrder);

        assertEquals(newOrder, waitingVerificationState.getOrder());
        assertEquals("Waiting for Verification", waitingVerificationState.getStatus());
    }

    @Test
    void testVerify() {
        // Verify method in WaitingVerificationState should do nothing
        assertDoesNotThrow(() -> waitingVerificationState.verify());
    }

    @Test
    void testCancel() {
        // Cancel method in WaitingVerificationState should do nothing
        assertDoesNotThrow(() -> waitingVerificationState.cancel());
    }

    @Test
    void testSetDelivery() {
        // SetDelivery method in WaitingVerificationState should throw IllegalStateException
        assertThrows(IllegalStateException.class, () -> waitingVerificationState.setDelivery("standard"));
    }

    @Test
    void testComplete() {
        // Complete method in WaitingVerificationState should throw IllegalStateException
        assertThrows(IllegalStateException.class, () -> waitingVerificationState.complete());
    }
}
