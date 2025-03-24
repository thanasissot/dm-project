package sotiroglou.athanasios.microservices.auth.catalogue.catalogue.dto;

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
