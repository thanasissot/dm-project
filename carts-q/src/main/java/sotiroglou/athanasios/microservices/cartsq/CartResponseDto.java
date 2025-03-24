package sotiroglou.athanasios.microservices.cartsq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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