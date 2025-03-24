package sotiroglou.athanasios.microservices.carts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sotiroglou.athanasios.microservices.carts.CartItem;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDto {
    private String cartId;
    private String customerId;
    private Set<CartItem> cartItems = new HashSet<>();
}