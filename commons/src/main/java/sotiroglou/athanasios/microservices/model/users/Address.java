package sotiroglou.athanasios.microservices.model.users;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Address extends PanacheMongoEntity {

    public String street;
    public long numberS;
    public String city;
    public String country;
    public String postCode;
    public ObjectId userId;
}