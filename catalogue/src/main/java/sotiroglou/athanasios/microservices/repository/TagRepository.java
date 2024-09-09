package sotiroglou.athanasios.microservices.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sotiroglou.athanasios.microservices.entity.Tag;

@ApplicationScoped
public class TagRepository implements PanacheMongoRepository<Tag> {

}