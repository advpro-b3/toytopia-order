package id.ac.ui.cs.advprog.toytopiaorder.repository;

import id.ac.ui.cs.advprog.toytopiaorder.model.state.CompletedState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedStateRepository extends JpaRepository<CompletedState, String> {
}
