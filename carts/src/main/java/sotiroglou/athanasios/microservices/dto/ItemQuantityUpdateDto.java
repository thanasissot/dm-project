package sotiroglou.athanasios.microservices.dto;

import lombok.Data;

@Data
public class ItemQuantityUpdateDto {
    private int quantity;
    private Long id;
}
