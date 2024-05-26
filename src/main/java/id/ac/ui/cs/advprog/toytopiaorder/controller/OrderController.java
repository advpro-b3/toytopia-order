package id.ac.ui.cs.advprog.toytopiaorder.controller;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.service.CartService;
import id.ac.ui.cs.advprog.toytopiaorder.service.OrderService;

import id.ac.ui.cs.advprog.toytopiaorder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;

    public OrderController(OrderService orderService, CartService cartService, UserService userService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @PostMapping("/checkout/{cartId}")
    public CompletableFuture<ResponseEntity<String>> checkout(@PathVariable("cartId") String cartId, @RequestHeader("Authorization") String token) {
        CompletableFuture<String> emailFuture = userService.getEmail(token);
        return emailFuture.thenApply(email -> {
            if (email != null) {
                Map<String, Object> cart = cartService.getCartById(cartId);
                if (cart != null) {
                    Order order = orderService.createOrderFromCart(email, (Double) cart.get("totalPrice"), cartId, (Map<String, Map<String, Object>>) cart.get("cartItems"));
                    if (order != null) {
                        return ResponseEntity.ok("Order created successfully with ID: " + order.getOrderId());
                    } else {
                        return ResponseEntity.badRequest().body("Failed to create order from cart.");
                    }
                } else {
                    return ResponseEntity.badRequest().body("Cart with ID " + cartId + " not found.");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        });
    }

    @PutMapping("/verify/{id}")
    public CompletableFuture<ResponseEntity<Order>> verifyOrder(@RequestHeader("Authorization") String token, @PathVariable("id") String orderId) {
        CompletableFuture<Boolean> isAdmin = userService.isAdmin(token);
        return isAdmin.handle((result, ex) -> {
            if (ex != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (!result) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            Order verified =  orderService.verifyOrder(orderId);
            return ResponseEntity.ok(verified);
        });
    }

    @PutMapping("/cancel/{id}")
    public CompletableFuture<ResponseEntity<Order>> cancelOrder(@RequestHeader("Authorization") String token, @PathVariable("id") String orderId) {
        CompletableFuture<Boolean> isAdmin = userService.isAdmin(token);
        return isAdmin.handle((result, ex) -> {
            if (ex != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (!result) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            Order canceled =  orderService.cancelOrder(orderId);
            return ResponseEntity.ok(canceled);
        });
    }

    @PutMapping("/setDeliveryMethod/{id}/{deliveryMethod}")
    public CompletableFuture<ResponseEntity<Order>> verifyOrder(@RequestHeader("Authorization") String token, @PathVariable("id") String orderId, @PathVariable("deliveryMethod") String deliveryMethod) {
        CompletableFuture<Boolean> isAdmin = userService.isAdmin(token);
        return isAdmin.handle((result, ex) -> {
            if (ex != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (!result) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            Order order =  orderService.setDeliveryMethod(orderId, deliveryMethod);
            return ResponseEntity.ok(order);
        });
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<Order> completeOrder(@PathVariable("id") String orderId) {
        Order completed =  orderService.completeOrder(orderId);
        return ResponseEntity.ok(completed);
    }

    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<List<List<String>>>> OrderListPage(@RequestHeader("Authorization") String token) {
        CompletableFuture<String> emailFuture = userService.getEmail(token);
        return emailFuture.thenApply((email) -> {
            if (email != null) {
                List<List<String>> allOrders = orderService.findByUserEmail(email);
                return ResponseEntity.ok(allOrders);
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        });
    }

    @GetMapping("/adminList")
    public CompletableFuture<ResponseEntity<List<List<String>>>> OrderListPageAdmin(@RequestHeader("Authorization") String token) {
        CompletableFuture<Boolean> isAdmin = userService.isAdmin(token);
        return isAdmin.handle((result, ex) -> {
            if (ex != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (!result) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            List<List<String>> allOrders = orderService.findAll();
            return ResponseEntity.ok(allOrders);
        });

    }
}
