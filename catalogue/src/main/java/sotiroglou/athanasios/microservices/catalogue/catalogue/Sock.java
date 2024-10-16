package sotiroglou.athanasios.microservices.catalogue.catalogue;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(database = "catalogue_db", collection="socks")
public class Sock extends PanacheMongoEntity {
    public String name;
    public String description;
    public float price;
    public int count;
    public List<String> imageUrls;
}