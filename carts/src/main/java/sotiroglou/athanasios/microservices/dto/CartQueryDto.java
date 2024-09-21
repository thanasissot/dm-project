package sotiroglou.athanasios.microservices.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.Set;

@Data
public class CartQueryDto {
    @Nullable
    private Long cartId;
    @Nullable
    private Long customerId;
    @Nullable
    private Set<CartItemDto> cartItems;
    @Nullable
    private Long cartItemIdForDelete;
}
