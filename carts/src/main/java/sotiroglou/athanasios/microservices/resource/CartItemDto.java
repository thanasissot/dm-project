package sotiroglou.athanasios.microservices.resource;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

@Builder
@Data
public class CartItemDto {
    public ObjectId cartId;
    public int quantity;
    public double unitPrice;
    public ObjectId productId;
}

