package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.OrderState;

public class InDeliveryState implements OrderState {
    private final Order order;
    public final String status;

    public InDeliveryState(Order order) {
        this.order = order;
        this.status = "In Delivery";
    }

    @Override
    public void verify() {
        throw new IllegalStateException("Cannot verify an order that is already in the delivery process");
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("Cannot cancel an order that is already in delivery");
    }

    @Override
    public void setDelivery(String method) {
        throw new IllegalStateException("Delivery method already set");
    }

    @Override
    public void complete() {
        order.setState(new CompletedState(order));
    }
}