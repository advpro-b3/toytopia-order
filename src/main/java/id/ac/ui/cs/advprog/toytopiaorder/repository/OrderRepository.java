package id.ac.ui.cs.advprog.toytopiaorder.repository;


import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
    Order findByOrderId(String orderId);
}