package sotiroglou.athanasios.microservices.carts.dto;

import lombok.Data;

@Data
public class ItemQuantityUpdateDto {
    private int quantity;
    private String id;
}
