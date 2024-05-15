package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("WaitingVerification")
public class WaitingVerificationState implements OrderState {
    @ManyToOne
    public Order order;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id = 1;
    public final String status = "Waiting Verification";

    public WaitingVerificationState(Order order) {
        this.order = order;
    }

    public WaitingVerificationState() {

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