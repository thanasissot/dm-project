package sotiroglou.athanasios.microservices.model.payment;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthoriseRequest {
    private float amount;
}
