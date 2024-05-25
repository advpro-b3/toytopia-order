package id.ac.ui.cs.advprog.toytopiaorder.model;

import id.ac.ui.cs.advprog.toytopiaorder.model.state.*;
import id.ac.ui.cs.advprog.toytopiaorder.enums.DeliveryMethod;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import lombok.Getter;

@Entity
@Getter
@Table(name = "orders")
public class Order {
    @Id
    private String orderId;
    private Double totalPrice;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "productId")
            private List<CartItem> cartItemMap;
    String deliveryMethod;
    String resiCode;
    @ManyToOne
    private AbstractOrderState state;

    public Order(Double totalPrice) {
        this.orderId = UUID.randomUUID().toString();;
        this.totalPrice = totalPrice;
    }

    public Order() {

    }

    public void addCartItem(List<CartItem> cart) {
        this.cartItemMap = cart;
    }

    public void setDeliveryMethod(String method) {
        state.setDelivery(method);
    }

    public void setDelivery(String method) {
        if (DeliveryMethod.contains(method)) {
            this.deliveryMethod = method;
            this.resiCode = setResiCode(method);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String status() {
        if (state instanceof WaitingVerificationState) {
            return "Waiting for Verification";
        }
        if (state instanceof SetDeliveryState) {
            return "Set Delivery";
        }
        if (state instanceof InDeliveryState) {
            return "In Delivery";
        }
        if (state instanceof CompletedState) {
            return "Completed";
        }
        else {
            return "Canceled";
        }
    }

    public void setState(AbstractOrderState state) {
        this.state = state;
    }

    public String setResiCode(String method) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder str = new StringBuilder(16);
        if (method.equals("JTE")) {
            str.append("JTE-");
        }
        else if (method.equals("GOBEK")) {
            str.append("GBK-");
        }
        else if (method.equals("SIWUZZ")) {
            str.append("SWZ-");
        }
        else {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < 12; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            str.append(randomChar);
        }
        return str.toString();
    }

    public void verify() {
        state.verify();
    }

    public void cancel() {
        state.cancel();
    }

    public void complete() {
        state.complete();
    }
}