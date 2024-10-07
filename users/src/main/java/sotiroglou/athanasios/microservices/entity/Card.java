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
@Table(name = "card")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card implements AddDomain {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String cardNumber;  // In a real system, this should be encrypted
    private String expires;
    private String ccv;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Embedded
    private Links links = new Links();

    @Override
    public void populateLinks(Links links, String domain) {
        links.addCardLink(domain, id.toString());
    }

}
