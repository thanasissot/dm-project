package sotiroglou.athanasios.microservices.cartsq;

import lombok.Data;

@Data
public class ItemQuantityUpdateDto {
    private int quantity;
    private String id;
}
