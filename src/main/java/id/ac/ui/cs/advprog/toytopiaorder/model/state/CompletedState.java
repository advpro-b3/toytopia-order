package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@DiscriminatorValue("Completed")
public class CompletedState implements OrderState {
    @ManyToOne
    public Order order;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id = 4;
    public final String status = "Completed";

    public CompletedState(Order order) {
        this.order = order;
    }

    public CompletedState() {

    }

    @Override
    public void verify() {
        throw new IllegalStateException("Cannot verify an order that is already completed");
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("Cannot cancel an order that is already completed");
    }

    @Override
    public void setDelivery(String method) {
        throw new IllegalStateException("Cannot set delivery method for an order that is already completed");
    }

    @Override
    public void complete() {
        throw new IllegalStateException("Order is already completed");
    }
}