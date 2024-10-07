package sotiroglou.athanasios.microservices.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import sotiroglou.athanasios.microservices.AddDomain;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"cards", "addresses"})
public class User implements AddDomain {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long customerId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Card> cards = new HashSet<>();

    @Embedded
    private Links links = new Links();

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String salt;


    @Override
    public void populateLinks(Links links, String domain) {
        links.addCustomerLinks(domain, id.toString());
    }

}