package sotiroglou.athanasios.microservices.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.persistence.Transient;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection="users")
public class User extends PanacheMongoEntity {

    // These fields will not be persisted
    @Transient
    public List<ObjectId> addressesIds;

    @Transient
    public List<ObjectId> cardsIds;

    public String firstName;
    public String lastName;
    public String email;
    public String username;
    public String password;
    public String salt;


}