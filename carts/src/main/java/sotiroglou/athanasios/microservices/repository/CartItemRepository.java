package sotiroglou.athanasios.microservices.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import sotiroglou.athanasios.microservices.entity.Cart;
import sotiroglou.athanasios.microservices.entity.CartItem;

import java.util.List;

@ApplicationScoped
public class CartItemRepository implements PanacheMongoRepository<CartItem> {
    // Custom database methods can be added here
    public List<CartItem> findAllCartItemsByCartId(ObjectId cartId) {
        return CartItem.find("cartId", cartId).list();
    }

}

