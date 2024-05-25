package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@DiscriminatorValue("WaitingVerification")
public class WaitingVerificationState extends AbstractOrderState {
    @ManyToOne
    public Order order;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id = 1;
    public String status;

    public WaitingVerificationState(Order order) {
        this.order = order;
        this.status = "Waiting for Verification";
    }

    public WaitingVerificationState() {

    }

    @Override
    public void verify() {
    }

    @Override
    public void cancel() {
    }

    @Override
    public void setDelivery(String method) {
        throw new IllegalStateException("Cannot set delivery method while waiting for verification");
    }

    @Override
    public void complete() {
        throw new IllegalStateException("Cannot complete an order while waiting for verification");
    }

    public void setOrder(Order order) {
        this.order = order;
        this.status = "Waiting for Verification";
    }
}