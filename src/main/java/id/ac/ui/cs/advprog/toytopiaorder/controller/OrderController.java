package id.ac.ui.cs.advprog.toytopiaorder.controller;

import id.ac.ui.cs.advprog.toytopiaorder.model.Cart;
import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.service.CartService;
import id.ac.ui.cs.advprog.toytopiaorder.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private final OrderService orderService;
    private final CartService cartService;

    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @PostMapping("/checkout/{cartId}")
    public ResponseEntity<String> checkout(@PathVariable("cartId") String cartId) {
        Cart cart = cartService.getCartById(cartId);
        if (cart != null) {
            Order order = orderService.createOrderFromCart(cart, cartId);
            if (order != null) {
                return ResponseEntity.ok("Order created successfully with ID: " + order.getId());
            } else {
                return ResponseEntity.badRequest().body("Failed to create order from cart.");
            }
        } else {
            return ResponseEntity.badRequest().body("Cart with ID " + cartId + " not found.");
        }
    }

    @PutMapping("/verify/{id}")
    public void verifyOrder(@PathVariable("id") String orderId) {
        orderService.verifyOrder(orderId);
    }

    @PutMapping("/cancel/{id}")
    public void cancelOrder(@PathVariable("id") String orderId) {
        orderService.cancelOrder(orderId);
    }

    @PutMapping("/setDeliveryMethod/{id}")
    public void setDeliveryMethod(@PathVariable("id") String orderId, String deliveryMethod) {
        orderService.setDeliveryMethod(orderId, deliveryMethod);
    }

    @PutMapping("/complete/{id}")
    public void completeOrder(@PathVariable("id") String orderId) {
        orderService.completeOrder(orderId);
    }

    @GetMapping("/list")
    public String OrderListPage(Model model) {
        List<Order> allOrders = orderService.findAll();
        model.addAttribute("orders", allOrders);
        return "BuyerOrderList";
    }

    @GetMapping("/list-json")
    public List<Order> OrderListJson(Model model) {
        List<Order> allOrders = orderService.findAll();
        return allOrders;
    }

    @GetMapping("/adminList")
    public String OrderListPageAdmin(Model model) {
        List<Order> allOrders = orderService.findAll();
        model.addAttribute("orders", allOrders);
        return "AdminOrderList";
    }
}
