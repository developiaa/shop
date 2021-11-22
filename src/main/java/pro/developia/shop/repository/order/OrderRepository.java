package pro.developia.shop.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.developia.shop.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
