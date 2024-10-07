package sotiroglou.athanasios.microservices.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "cards")
public class Card extends PanacheMongoEntity {
    public String number;
    public String expiryMonth;
    public String expiryYear;
    public String ccv;
    public String name;
}