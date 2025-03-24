package sotiroglou.athanasios.microservices.auth.model.catalogue;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SockTag extends PanacheMongoEntity {
    public ObjectId sockId;
    public ObjectId tagId;
}