package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;

public class CanceledState implements OrderState {
    private final Order order;
    public final String status;

    public CanceledState(Order order) {
        this.order = order;
        this.status = "Canceled";
    }

    @Override
    public void verify() {
        throw new IllegalStateException("Cannot verify an order that is already canceled");
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("Order is already canceled");
    }

    @Override
    public void setDelivery(String method) {
        throw new IllegalStateException("Cannot set delivery method for an order that is already canceled");
    }

    @Override
    public void complete() {
        throw new IllegalStateException("Cannot complete an order that is already canceled");
    }
}
