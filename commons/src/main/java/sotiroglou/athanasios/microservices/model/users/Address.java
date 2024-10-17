package sotiroglou.athanasios.microservices.model.users;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection="addresses")
public class Address extends PanacheMongoEntity {

    public String street;
    public long numberS;
    public String city;
    public String country;
    public String postCode;
    public ObjectId userId;
}