package sotiroglou.athanasios.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sotiroglou.athanasios.microservices.entity.Cart;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByCustomerId(Long customerId);

}
