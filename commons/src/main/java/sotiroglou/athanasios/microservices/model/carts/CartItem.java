package sotiroglou.athanasios.microservices.model.carts;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection="cart-items")
public class CartItem extends PanacheMongoEntity {

    public ObjectId cartId;

    public int quantity;

    public ObjectId productId;
}