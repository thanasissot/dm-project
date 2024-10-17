package sotiroglou.athanasios.microservices.users.users;

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
public class User extends PanacheMongoEntity {

    public ObjectId customerId;
    public String firstName;
    public String lastName;
    public String email;
    public String username;
    public String password;
    public String salt;

}