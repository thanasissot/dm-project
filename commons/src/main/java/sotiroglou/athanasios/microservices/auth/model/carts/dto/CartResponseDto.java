package sotiroglou.athanasios.microservices.auth.model.carts.dto;

import lombok.*;
import sotiroglou.athanasios.microservices.auth.model.carts.CartItem;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CartResponseDto {

    private String cartId;

    private String customerId;

    private Set<CartItem> cartItems;
}
