package sotiroglou.athanasios.microservices.catalogue.catalogue;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(database = "catalogue_db", collection="sockTags")
public class SockTag extends PanacheMongoEntity {
    public ObjectId sockId;
    public ObjectId tagId;
}