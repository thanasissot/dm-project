package sotiroglou.athanasios.microservices.model.catalogue;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Sock extends PanacheMongoEntity {
    public String name;
    public String description;
    public float price;
    public int count;
    public List<String> imageUrls;
}