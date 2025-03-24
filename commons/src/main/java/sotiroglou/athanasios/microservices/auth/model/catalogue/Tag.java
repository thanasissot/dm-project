package sotiroglou.athanasios.microservices.auth.model.catalogue;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Tag extends PanacheMongoEntity {
    public String name;
}