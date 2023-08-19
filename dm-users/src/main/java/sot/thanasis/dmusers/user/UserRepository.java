package sot.thanasis.dmusers.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom query methods here if needed
    Optional<List<User>> findByUsernameContaining(String keyword);
}
