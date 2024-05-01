package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;

public class WaitingVerificationState implements OrderState {
    private final Order order;

    public WaitingVerificationState(Order order) {
        this.order = order;
    }

    @Override
    public void verify() {
        order.setState(new SetDeliveryState(order));
    }

    @Override
    public void cancel() {
        order.setState(new CanceledState(order));
    }

    @Override
    public void setDelivery(String method) {
        throw new IllegalStateException("Cannot set delivery method while waiting for verification");
    }

    @Override
    public void complete() {
        throw new IllegalStateException("Cannot complete an order while waiting for verification");
    }
}