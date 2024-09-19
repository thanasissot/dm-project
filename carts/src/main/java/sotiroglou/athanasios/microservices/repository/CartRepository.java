package sotiroglou.athanasios.microservices.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import sotiroglou.athanasios.microservices.entity.Cart;

import java.util.List;

@ApplicationScoped
public class CartRepository implements PanacheMongoRepository<Cart> {
    // Custom database methods can be added here
    public List<Cart> findAllCarts() {
        return Cart.findAll().list();
    }

    public List<Cart> findAllCartsByCustomerId(ObjectId customerId) {
        return Cart.find("customerId", customerId).list();
    }

    public List<Cart> findAllCartsByCustomerIdAndSession(ObjectId customerId, String session) {
        List<Cart> carts = Cart.find("{'customerId': ?1, 'session': ?2}", customerId, session).list();
        System.out.println(carts);
        return carts;
    }


}

