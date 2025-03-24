package sotiroglou.athanasios.microservices.catalogue.catalogue;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(database = "catalogue_db", collection="tags")
public class Tag extends PanacheMongoEntity {
    public String name;
}