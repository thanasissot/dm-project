package sotiroglou.athanasios.microservices.model.carts;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection="carts")
public class Cart extends PanacheMongoEntity {

    public ObjectId customerId;

    public Set<CartItem> cartItems = new HashSet<>();

}