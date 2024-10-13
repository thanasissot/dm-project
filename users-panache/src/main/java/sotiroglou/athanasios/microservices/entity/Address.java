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
@MongoEntity(collection="users")
public class Address extends PanacheMongoEntity {

    public String street;
    public Long number;
    public String city;
    public String country;
    public String postCode;
    public ObjectId userId;
}