package sotiroglou.athanasios.microservices.model.carts.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ItemQuantityUpdateDto {
    private int quantity;
    private String id;
}
