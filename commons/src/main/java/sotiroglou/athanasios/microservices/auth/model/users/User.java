package sotiroglou.athanasios.microservices.auth.model.users;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class User extends PanacheMongoEntity {

    public List<Address> addresses;
    public List<Card> cards;

    public ObjectId customerId;
    public String firstName;
    public String lastName;
    public String email;
    public String username;
    public String password;
    public String salt;

}