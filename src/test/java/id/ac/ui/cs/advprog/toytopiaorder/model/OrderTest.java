package id.ac.ui.cs.advprog.toytopiaorder.model;

import org.springframework.boot.test.context.SpringBootTest;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order("abc123", 100.0);
    }

    @Test
    void testAddCartItem() {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem item1 = new CartItem("1", "Toy 1", 2, 20.0, order);
        CartItem item2 = new CartItem("2", "Toy 2", 1, 30.0, order);
        cartItems.add(item1);
        cartItems.add(item2);

        order.addCartItem(cartItems);

        assertEquals(2, order.getCartItemMap().size());
    }

    @Test
    void testSetDeliveryMethod() {
        AbstractOrderState state = mock(AbstractOrderState.class);
        order.setState(state);

        order.setDeliveryMethod("JTE");

        verify(state, times(1)).setDelivery("JTE");
    }

    @Test
    void testSetDelivery() {
        order.setDelivery("JTE");
        assertEquals("JTE", order.getDeliveryMethod());
    }

    @Test
    void testSetDeliveryInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            order.setDelivery("TIKA");
        });
    }

    @Test
    void testSetResiCodeJTE() {
        order.setDelivery("JTE");

        assertTrue(order.getResiCode().startsWith("JTE-"));
        assertEquals(16, order.getResiCode().length());
    }

    @Test
    void testSetResiCodeGBK() {
        String resiCode = order.setResiCode("GOBEK");

        assertTrue(resiCode.startsWith("GBK-"));
        assertEquals(16, resiCode.length());
    }

    @Test
    void testSetResiCodeSWZ() {
        String resiCode = order.setResiCode("SIWUZZ");

        assertTrue(resiCode.startsWith("SWZ-"));
        assertEquals(16, resiCode.length());
    }

    @Test
    void testSetResiCodeInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            order.setResiCode("TIKA");
        });
    }

    @Test
    void testStatus() {
        WaitingVerificationState waitingVerificationState = new WaitingVerificationState(order);
        order.setState(waitingVerificationState);

        assertEquals("Waiting for Verification", order.status());

        SetDeliveryState setDeliveryState = new SetDeliveryState(order);
        order.setState(setDeliveryState);

        assertEquals("Set Delivery", order.status());

        InDeliveryState inDeliveryState = new InDeliveryState(order);
        order.setState(inDeliveryState);

        assertEquals("In Delivery", order.status());

        CompletedState completedState = new CompletedState(order);
        order.setState(completedState);

        assertEquals("Completed", order.status());

        CanceledState canceledState = new CanceledState(order);
        order.setState(canceledState);

        assertEquals("Canceled", order.status());
    }

    @Test
    void testVerify() {
        AbstractOrderState state = mock(AbstractOrderState.class);
        order.setState(state);

        order.verify();

        verify(state, times(1)).verify();
    }

    @Test
    void testCancel() {
        AbstractOrderState state = mock(AbstractOrderState.class);
        order.setState(state);

        order.cancel();

        verify(state, times(1)).cancel();
    }

    @Test
    void testComplete() {
        AbstractOrderState state = mock(AbstractOrderState.class);
        order.setState(state);

        order.complete();

        verify(state, times(1)).complete();
    }
}