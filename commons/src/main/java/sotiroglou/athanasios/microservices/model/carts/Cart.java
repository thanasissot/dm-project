package sotiroglou.athanasios.microservices.model.carts;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Cart extends PanacheMongoEntity {

    public ObjectId customerId;

    public Set<CartItem> cartItems = new HashSet<>();

}