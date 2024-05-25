package id.ac.ui.cs.advprog.toytopiaorder.repository;

import id.ac.ui.cs.advprog.toytopiaorder.model.state.WaitingVerificationState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingVerificationStateRepository extends JpaRepository<WaitingVerificationState, String> {
}
