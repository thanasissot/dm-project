package sotiroglou.athanasios.microservices.payments.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthoriseResponse {
    private boolean authorised;
    private String message;
}
