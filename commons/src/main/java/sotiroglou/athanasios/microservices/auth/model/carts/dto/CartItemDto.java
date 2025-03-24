package sotiroglou.athanasios.microservices.auth.model.carts.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CartItemDto {
    private String id;
    private Integer quantity;
    private Double unitPrice;
    private String cartId;
    private String productId;
}

