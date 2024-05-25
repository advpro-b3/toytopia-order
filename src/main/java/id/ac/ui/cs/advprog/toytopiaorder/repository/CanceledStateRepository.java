package id.ac.ui.cs.advprog.toytopiaorder.repository;

import id.ac.ui.cs.advprog.toytopiaorder.model.state.CanceledState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanceledStateRepository extends JpaRepository<CanceledState, String> {
}
