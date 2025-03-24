package sotiroglou.athanasios.microservices.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserIsAuthenticated {
    public boolean authenticated;
}
