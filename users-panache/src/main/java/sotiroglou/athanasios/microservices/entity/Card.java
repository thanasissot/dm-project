package sotiroglou.athanasios.microservices.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection="cards")
public class Card extends PanacheMongoEntity {

    public String cardNumber;  // In a real system, this should be encrypted
    public String expires;
    public String ccv;
    public ObjectId userId;
}
