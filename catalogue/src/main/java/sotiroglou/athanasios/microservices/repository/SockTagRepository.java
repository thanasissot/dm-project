package sotiroglou.athanasios.microservices.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sotiroglou.athanasios.microservices.entity.SockTag;

@ApplicationScoped
public class SockTagRepository implements PanacheMongoRepository<SockTag> {

}