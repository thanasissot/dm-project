package sotiroglou.athanasios.microservices;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import sotiroglou.athanasios.microservices.model.users.User;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection="orders")
public class Order extends PanacheMongoEntity {
    public ObjectId customerId;
    public LocalDate orderDate;

}
