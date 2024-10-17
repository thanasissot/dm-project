package sotiroglou.athanasios.microservices.catalogue.catalogue.dto;

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
