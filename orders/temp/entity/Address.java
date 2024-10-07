package sotiroglou.athanasios.microservices.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "addresses")
public class Address extends PanacheMongoEntity {
    public String street;
    public String city;
    public String state;
    public String zipCode;
    public String country;
}