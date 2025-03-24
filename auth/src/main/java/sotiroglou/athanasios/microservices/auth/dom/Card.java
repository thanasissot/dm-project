package sotiroglou.athanasios.microservices.auth.dom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue
    private Long cardId;

    private String cardNumber;  // In a real system, this should be encrypted
    private String expires;
    private String ccv;

    @ManyToOne
    @JoinColumn(name = "auth_user_id")
    private AuthUser user;

    @Override
    public String toString() {
        return String.format("%s %s %s", cardNumber, expires, ccv);
    }
}