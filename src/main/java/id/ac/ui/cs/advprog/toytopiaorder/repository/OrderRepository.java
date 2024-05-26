package id.ac.ui.cs.advprog.toytopiaorder.repository;


import id.ac.ui.cs.advprog.toytopiaorder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    Order findByOrderId(String orderId);
    List<Order> findByUserEmail(String userEmail);

    @Query(value = "SELECT o.user_email, o.order_id, ci.cart_data, o.delivery_method, o.resi_code, o.total_price, s.status " +
            "FROM orders o " +
            "JOIN abstract_order_state s ON s.id = o.state_id " +
            "LEFT JOIN ( " +
            "   SELECT order_order_id, STRING_AGG(quantity || ' pcs ' || product_name || ' Rp' || price, ', ') AS cart_data " +
            "   FROM cart_item " +
            "   GROUP BY order_order_id " +
            ") ci ON o.order_id = ci.order_order_id",
            nativeQuery = true)
    List<List<String>> retrieveAllOrder();

    @Query(value = "SELECT o.user_email, o.order_id, ci.cart_data, o.delivery_method, o.resi_code, o.total_price, s.status " +
            "FROM orders o " +
            "JOIN abstract_order_state s ON s.id = o.state_id " +
            "LEFT JOIN ( " +
            "   SELECT order_order_id, STRING_AGG(quantity || ' pcs ' || product_name || ' Rp' || price, ', ') AS cart_data " +
            "   FROM cart_item " +
            "   GROUP BY order_order_id " +
            ") ci ON o.order_id = ci.order_order_id " +
            "WHERE o.user_email = :userEmail",
            nativeQuery = true)
    List<List<String>> retrieveOrderByEmail(@Param("userEmail") String userEmail);
}