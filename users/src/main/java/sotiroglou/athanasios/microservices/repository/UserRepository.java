package sotiroglou.athanasios.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sotiroglou.athanasios.microservices.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
