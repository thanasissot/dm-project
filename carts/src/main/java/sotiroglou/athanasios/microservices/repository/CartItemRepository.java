package sotiroglou.athanasios.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sotiroglou.athanasios.microservices.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
