package id.ac.ui.cs.advprog.toytopiaorder.model;

import id.ac.ui.cs.advprog.toytopiaorder.model.Cart;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.InDeliveryState;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.OrderState;
import id.ac.ui.cs.advprog.toytopiaorder.enums.DeliveryMethod;

import java.util.UUID;
import java.util.Random;

import id.ac.ui.cs.advprog.toytopiaorder.model.state.SetDeliveryState;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.WaitingVerificationState;
import lombok.Getter;

@Getter
public class Order {
    String id;
    Cart cart;
    String deliveryMethod;
    String resiCode;
    OrderState state;

    public Order(Cart cart) {
        this.id = cart.getId();
        this.cart = cart;
        this.state = new WaitingVerificationState(this);
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
    public void setState(OrderState state) {
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