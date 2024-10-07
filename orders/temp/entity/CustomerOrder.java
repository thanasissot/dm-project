package sotiroglou.athanasios.microservices.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "orders")
public class CustomerOrder extends PanacheMongoEntity {
    public String customerId;
    public List<Item> items;
    public String shipmentId;
    public LocalDate orderDate;
}