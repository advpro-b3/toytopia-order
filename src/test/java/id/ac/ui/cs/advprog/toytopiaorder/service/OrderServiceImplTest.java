package id.ac.ui.cs.advprog.toytopiaorder.service;

import id.ac.ui.cs.advprog.toytopiaorder.model.*;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.*;
import id.ac.ui.cs.advprog.toytopiaorder.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private WaitingVerificationStateRepository waitingVerificationStateRepository;

    @Mock
    private SetDeliveryStateRepository setDeliveryStateRepository;

    @Mock
    private InDeliveryStateRepository inDeliveryStateRepository;

    @Mock
    private CompletedStateRepository completedStateRepository;

    @Mock
    private CanceledStateRepository canceledStateRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testCreateOrderFromCart() {
        Map<String, Map<String, Object>> cart = new HashMap<>();
        Map<String, Object> item1 = new HashMap<>();
        item1.put("productId", "1");
        item1.put("name", "Toy 1");
        item1.put("quantity", 2);
        item1.put("price", 10.0);
        cart.put("item1", item1);

        when(orderRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(waitingVerificationStateRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Order order = orderService.createOrderFromCart("sab@mail", 20.0, "cart123", cart);

        assertNotNull(order);
        assertEquals(20.0, order.getTotalPrice());
        assertEquals(1, order.getCartItemMap().size());
        assertInstanceOf(WaitingVerificationState.class, order.getState());
    }

    @Test
    public void testFindByOrderId() {
        Map<String, Map<String, Object>> cart = new HashMap<>();
        Map<String, Object> item1 = new HashMap<>();
        item1.put("productId", "1");
        item1.put("name", "Toy 1");
        item1.put("quantity", 2);
        item1.put("price", 10.0);
        cart.put("item1", item1);

        when(orderRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(waitingVerificationStateRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Order order = orderService.createOrderFromCart("sab@mail", 20.0, "cart123", cart);

        Order tested = orderService.findByOrderId(order.getOrderId());

        assertNull(tested);
    }

    @Test
    public void testFindAll() {

        List<List<String>> tested = orderService.findAll();

        assertNotNull(tested);
    }

    @Test
    public void testFindByUserEmail() {
        Map<String, Map<String, Object>> cart = new HashMap<>();
        Map<String, Object> item1 = new HashMap<>();
        item1.put("productId", "1");
        item1.put("name", "Toy 1");
        item1.put("quantity", 2);
        item1.put("price", 10.0);
        cart.put("item1", item1);

        when(orderRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(waitingVerificationStateRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Order order = orderService.createOrderFromCart("sab@mail", 20.0, "cart123", cart);

        List<List<String>> tested = orderService.findByUserEmail("sab@mail");

        assertNotNull(tested);
    }
}

