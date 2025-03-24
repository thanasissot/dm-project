package sotiroglou.athanasios.microservices.model.payment;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthoriseResponse {
    private boolean authorised;
    private String message;
}
