package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.enums.DeliveryMethod;
import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.OrderState;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("SetDelivery")
public class SetDeliveryState extends AbstractOrderState {
    @ManyToOne
    public Order order;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id = 2;
    public String status;

    public SetDeliveryState(Order order) {
        this.order = order;
        this.status = "Set Delivery";
    }

    public SetDeliveryState() {

    }

    @Override
    public void verify() {
        throw new IllegalStateException("Cannot verify an order that is already in the delivery process");
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("Cannot cancel an order that is already in the delivery process");
    }

    @Override
    public void setDelivery(String method) {
        order.setDelivery(method);
    }

    @Override
    public void complete() {
        throw new IllegalStateException("Cannot complete an order without setting the delivery method");
    }
}