package sotiroglou.athanasios.microservices.auth.model.carts.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CreateCartDto {
    private String customerId;

}
