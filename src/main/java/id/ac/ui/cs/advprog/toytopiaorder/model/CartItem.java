package id.ac.ui.cs.advprog.toytopiaorder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
public class CartItem {
    @Id
    private String cartId;
    private String productId;
    private String productName;
    private int quantity;
    private Double price;

    @ManyToOne
    private Order order;

    // Constructors, getters, and setters

    public CartItem() {
    }

    public CartItem(String productId, String name, int quantity, Double price, Order order) {
        this.cartId = UUID.randomUUID().toString();
        this.productId = productId;
        this.productName = name;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }

    // Other methods
}
