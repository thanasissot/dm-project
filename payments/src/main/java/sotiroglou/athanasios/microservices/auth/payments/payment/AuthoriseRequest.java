package sotiroglou.athanasios.microservices.payments.payment;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthoriseRequest {
    private float amount;
}
