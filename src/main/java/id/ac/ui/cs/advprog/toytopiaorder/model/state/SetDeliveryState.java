package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.enums.DeliveryMethod;
import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.OrderState;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("SetDelivery")
public class SetDeliveryState implements OrderState {
    @ManyToOne
    public Order order;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id = 2;
    public final String status = "Set Delivery";

    public SetDeliveryState(Order order) {
        this.order = order;
    }

    public SetDeliveryState() {

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