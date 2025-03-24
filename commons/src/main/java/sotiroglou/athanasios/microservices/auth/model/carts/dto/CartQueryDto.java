package sotiroglou.athanasios.microservices.auth.model.carts.dto;

import jakarta.annotation.Nullable;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CartQueryDto {
    @Nullable
    private String cartId;
    @Nullable
    private String customerId;
    @Nullable
    private Set<CartItemDto> cartItems;
    @Nullable
    private String cartItemIdForDelete;
    @Nullable
    private ItemQuantityUpdateDto itemQuantityUpdate;

}
