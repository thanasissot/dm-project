package sotiroglou.athanasios.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sotiroglou.athanasios.microservices.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
