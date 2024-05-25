package id.ac.ui.cs.advprog.toytopiaorder.model.state;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractOrderState implements OrderState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;

    public String getStateName() {
        return status;
    }

    public void setStateName(String stateName) {
        this.status = stateName;
    }
}