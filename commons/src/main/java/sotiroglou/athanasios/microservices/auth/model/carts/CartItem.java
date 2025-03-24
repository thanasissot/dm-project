package sotiroglou.athanasios.microservices.model.carts;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CartItem extends PanacheMongoEntity{

    public ObjectId cartId;

    public int quantity;

    public ObjectId productId;
}