package sotiroglou.athanasios.microservices.auth.dom;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
public class AuthUser {
    @Id
    @GeneratedValue
    private Long authUserId;
    private String username;
    private String password;
    // Fields from User.java
    private String customerId; // String representation of ObjectId
    private String firstName;
    private String lastName;
    private String email;
    private String salt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<AuthRole> authRoles = new ArrayList<>();

}
