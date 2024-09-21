package sotiroglou.athanasios.microservices.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class CartDto {
    private Long customerId;
    private Set<CartItemDto> cartItems;

}
