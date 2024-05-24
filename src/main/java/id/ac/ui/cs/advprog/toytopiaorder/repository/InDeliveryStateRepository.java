package id.ac.ui.cs.advprog.toytopiaorder.repository;

import id.ac.ui.cs.advprog.toytopiaorder.model.state.InDeliveryState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InDeliveryStateRepository extends JpaRepository<InDeliveryState, String> {
}
