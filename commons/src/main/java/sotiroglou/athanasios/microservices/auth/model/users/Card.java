package sotiroglou.athanasios.microservices.auth.model.users;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Card extends PanacheMongoEntity {

    public String cardNumber;  // In a real system, this should be encrypted
    public String expires;
    public String ccv;
    public ObjectId userId;

    @Override
    public String toString() {
        return String.format("%s %s %s", cardNumber, expires, ccv);
    }
}
