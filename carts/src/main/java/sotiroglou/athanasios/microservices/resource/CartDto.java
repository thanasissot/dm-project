package sotiroglou.athanasios.microservices.resource;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Builder
@Data
public class CartDto {
    public ObjectId customerId;
    public String session;
    public List<CartItemDto> cartItems;
}
