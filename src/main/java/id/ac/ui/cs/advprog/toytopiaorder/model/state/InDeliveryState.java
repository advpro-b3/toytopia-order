package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.OrderState;

public class InDeliveryState implements OrderState {

    @Override
    public void changeState(Order order) {
        order.setStatus("COMPLETED");
    }
}