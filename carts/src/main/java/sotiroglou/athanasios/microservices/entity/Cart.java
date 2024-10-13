package sotiroglou.athanasios.microservices.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.persistence.*;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection="carts")
public class Cart extends PanacheMongoEntity {

    @Column(unique = true, nullable = false)
    public ObjectId customerId;

    public Set<CartItem> cartItems = new HashSet<>();

}