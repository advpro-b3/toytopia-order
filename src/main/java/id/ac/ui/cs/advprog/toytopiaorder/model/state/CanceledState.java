package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("Canceled")
public class CanceledState implements OrderState {
    @ManyToOne
    public Order order;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id = 5;
    public final String status = "Canceled";
    public CanceledState(Order order) {
        this.order = order;
    }

    public CanceledState() {

    }

    @Override
    public void verify() {
        throw new IllegalStateException("Cannot verify an order that is already canceled");
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("Order is already canceled");
    }

    @Override
    public void setDelivery(String method) {
        throw new IllegalStateException("Cannot set delivery method for an order that is already canceled");
    }

    @Override
    public void complete() {
        throw new IllegalStateException("Cannot complete an order that is already canceled");
    }
}
