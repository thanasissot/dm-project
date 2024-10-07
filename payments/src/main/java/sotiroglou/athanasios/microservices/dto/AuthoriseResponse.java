package sotiroglou.athanasios.microservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthoriseResponse {
    private boolean authorised;
    private String message;
}
