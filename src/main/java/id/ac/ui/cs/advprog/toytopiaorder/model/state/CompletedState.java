package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;


public class CompletedState implements OrderState {
    private final Order order;

    public CompletedState(Order order) {
        this.order = order;
    }
    @Override
    public void verify() {
        throw new IllegalStateException("Cannot verify an order that is already completed");
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("Cannot cancel an order that is already completed");
    }

    @Override
    public void setDelivery(String method) {
        throw new IllegalStateException("Cannot set delivery method for an order that is already completed");
    }

    @Override
    public void complete() {
        throw new IllegalStateException("Order is already completed");
    }
}