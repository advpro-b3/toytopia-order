package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;

public interface OrderState {
    void verify();
    void cancel();
    void setDelivery(String method);
    void complete();
}