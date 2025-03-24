package sotiroglou.athanasios.microservices.auth.model.catalogue.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SockPriceDto {
    private String sockId;
    private float price;
}
