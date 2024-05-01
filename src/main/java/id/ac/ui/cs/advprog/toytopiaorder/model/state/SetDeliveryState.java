package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.enums.DeliveryMethod;
import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.OrderState;

public class SetDeliveryState implements OrderState {
    private final Order order;

    public SetDeliveryState(Order order) {
        this.order = order;
    }

    @Override
    public void verify() {
        throw new IllegalStateException("Cannot verify an order that is already in the delivery process");
    }

    @Override
    public void cancel() {
        order.setState(new CanceledState(order));
    }

    @Override
    public void setDelivery(String method) {
        order.setDelivery(method);
        order.setState(new InDeliveryState(order));
    }

    @Override
    public void complete() {
        throw new IllegalStateException("Cannot complete an order without setting the delivery method");
    }
}