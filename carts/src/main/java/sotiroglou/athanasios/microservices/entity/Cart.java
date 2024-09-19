package sotiroglou.athanasios.microservices.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.beans.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@MongoEntity(database = "mydatabase")
public class Cart extends PanacheMongoEntity {
    private ObjectId customerId;
    private String session;

}