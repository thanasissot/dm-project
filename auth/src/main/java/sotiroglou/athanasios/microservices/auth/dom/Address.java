package sotiroglou.athanasios.microservices.auth.dom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    private Long addressId;

    private String street;
    private long numberS;
    private String city;
    private String country;
    private String postCode;

    @ManyToOne
    @JoinColumn(name = "auth_user_id")
    private AuthUser user;

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", street, numberS, city, country, postCode);
    }
}