package sotiroglou.athanasios.microservices.auth.users.users;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(database = "users_db", collection="addresses")
public class Address extends PanacheMongoEntity {

    public String street;
    public long numberS;
    public String city;
    public String country;
    public String postCode;
    public ObjectId userId;

    public String toString() {
        return String.format("%s %s %s %s %s", street, numberS, city, country, postCode);
    }
}