package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import id.ac.ui.cs.advprog.toytopiaorder.model.state.OrderState;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("InDelivery")
public class InDeliveryState extends AbstractOrderState {
    @ManyToOne
    public Order order;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id = 3;
    public String status;

    public InDeliveryState(Order order) {
        this.order = order;
        this.status = "In Delivery";
    }

    public InDeliveryState() {

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
    }
}