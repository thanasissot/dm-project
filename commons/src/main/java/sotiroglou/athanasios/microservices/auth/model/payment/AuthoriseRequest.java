package sotiroglou.athanasios.microservices.auth.model.payment;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthoriseRequest {
    private float amount;
}
