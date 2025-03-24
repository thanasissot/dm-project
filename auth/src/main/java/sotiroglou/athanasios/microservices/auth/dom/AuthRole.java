package sotiroglou.athanasios.microservices.auth.dom;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AuthRole {
    @Id
    @GeneratedValue
    private Long authRoleId;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public enum RoleName {
        ADMIN, USER, SUPERADMIN
    }

}