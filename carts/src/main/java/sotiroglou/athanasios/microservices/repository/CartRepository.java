package sotiroglou.athanasios.microservices.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sotiroglou.athanasios.microservices.entity.Cart;

import java.util.List;

@ApplicationScoped
public class CartRepository implements PanacheMongoRepository<Cart> {
    // Custom database methods can be added here
    public List<Cart> findAllCarts() {
        return Cart.findAll().list();
    }
}

