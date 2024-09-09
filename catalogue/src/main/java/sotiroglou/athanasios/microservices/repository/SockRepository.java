package sotiroglou.athanasios.microservices.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sotiroglou.athanasios.microservices.entity.Sock;

import java.util.List;


@ApplicationScoped
public class SockRepository implements PanacheMongoRepository<Sock> {
    public Sock findByName(String name) {
        return find("name", name).firstResult();
    }

    public List<Sock> findByTags(List<String> tags) {
        return find("{tags: {$in: ?1}}", tags).list();
    }
}