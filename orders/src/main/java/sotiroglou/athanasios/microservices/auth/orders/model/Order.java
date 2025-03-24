package sotiroglou.athanasios.microservices.auth.orders.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(database = "orders_db", collection="orders")
public class Order extends PanacheMongoEntity {
    public ObjectId customerId;
    public LocalDate orderDate;
    public float totalAmount;
    public String shippingAddress;
    public String cardDetails;
}
