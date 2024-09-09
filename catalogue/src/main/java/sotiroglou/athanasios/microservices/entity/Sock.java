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
@MongoEntity(collection="socks")
public class Sock extends PanacheMongoEntity {
    public String name;
    public String description;
    public float price;
    public int count;
    public List<String> imageUrls;
}