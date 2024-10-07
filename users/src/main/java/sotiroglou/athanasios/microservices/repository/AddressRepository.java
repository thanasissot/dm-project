package sotiroglou.athanasios.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sotiroglou.athanasios.microservices.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
