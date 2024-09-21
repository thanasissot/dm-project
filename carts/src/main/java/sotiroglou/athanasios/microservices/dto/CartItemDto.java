package sotiroglou.athanasios.microservices.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItemDto {
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private Long cartId;
    private Long productId;
}

