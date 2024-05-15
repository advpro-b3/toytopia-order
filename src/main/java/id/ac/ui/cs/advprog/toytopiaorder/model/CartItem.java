package id.ac.ui.cs.advprog.toytopiaorder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {
    @Id
    private String cartId;
    private Long productId;
    private String productName;
    private int quantity;
    private Long price;

    @ManyToOne
    private Order order;

    // Constructors, getters, and setters

    public CartItem() {
    }

    public CartItem(String cartId, Long productId, String name, int quantity, Long price, Order order) {
        this.cartId = cartId;
        this.productId = productId;
        this.productName = name;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }

    // Other methods
}
