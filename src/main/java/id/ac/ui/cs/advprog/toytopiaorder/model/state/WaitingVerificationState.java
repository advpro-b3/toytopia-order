package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.OrderState;

public class WaitingVerificationState implements OrderState {

    @Override
    public void changeState(Order order) {
        order.setStatus("WAITING_VERIFICATION");
    }

    public void verifOrder(Order order) {
        order.setStatus("SET_DELIVERY");
    }
    public void cancelOrder(Order order) {
        order.setStatus("CANCELED");
    }
}