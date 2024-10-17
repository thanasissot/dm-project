package sotiroglou.athanasios.carts.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.Set;

@Data
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
