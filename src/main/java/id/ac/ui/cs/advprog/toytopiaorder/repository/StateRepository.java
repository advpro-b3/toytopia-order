package id.ac.ui.cs.advprog.toytopiaorder.repository;


import id.ac.ui.cs.advprog.toytopiaorder.model.state.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<AbstractOrderState, String> {
}

