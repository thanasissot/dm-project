package sotiroglou.athanasios.microservices.carts.dto;

import lombok.Builder;
import lombok.Data;
import sotiroglou.athanasios.microservices.carts.carts.CartItem;

import java.util.Set;

@Data
@Builder
public class CartResponseDto {
    private String cartId;

    private String customerId;

    private Set<CartItem> cartItems;
}
