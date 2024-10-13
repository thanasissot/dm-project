package sotiroglou.athanasios.microservices.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItemDto {
    private String id;
    private Integer quantity;
    private Double unitPrice;
    private String cartId;
    private String productId;
}

