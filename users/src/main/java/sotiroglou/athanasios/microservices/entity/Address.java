package sotiroglou.athanasios.microservices.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sotiroglou.athanasios.microservices.AddDomain;

@Data
@Entity
@Table(name = "address")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address implements AddDomain {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String street;
    private Long number;
    private String city;
    private String country;
    private String postCode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    public User user;

    @Embedded
    private Links links = new Links();

    @Override
    public void populateLinks(Links links, String domain) {
        links.addAddressLink(domain, id.toString());
    }
}