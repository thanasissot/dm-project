package sotiroglou.athanasios.microservices.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection="users")
public class User extends PanacheMongoEntity {

    // These fields will not be persisted
    @JsonIgnore
    public List<Address> addresses;

    @JsonIgnore
    public List<Card> cards;

    public ObjectId customerId;
    public String firstName;
    public String lastName;
    public String email;
    public String username;
    public String password;
    public String salt;

}