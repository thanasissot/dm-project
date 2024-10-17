package sotiroglou.athanasios.microservices.carts.carts;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection="cartitems")
public class CartItem extends PanacheMongoEntity {

    public ObjectId cartId;

    public int quantity;

    public ObjectId productId;
}