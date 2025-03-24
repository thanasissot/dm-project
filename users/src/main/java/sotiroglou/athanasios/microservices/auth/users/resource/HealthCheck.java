package sotiroglou.athanasios.microservices.auth.users.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sotiroglou.athanasios.microservices.auth.users.users.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Path("/health")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HealthCheck {
    @GET
    @Path("/health")
    public List<Health> getHealth() {
        List<Health> health = new ArrayList<>();

        Health serviceHealth = new Health();
        serviceHealth.service = "catalogue";
        serviceHealth.status = "OK";
        serviceHealth.time = LocalDateTime.now().toString();

        Health dbHealth = new Health();
        dbHealth.service = "catalogue-db";
        dbHealth.status = isDatabaseHealthy() ? "OK" : "err";
        dbHealth.time = LocalDateTime.now().toString();

        health.add(serviceHealth);
        health.add(dbHealth);

        return health;
    }

    private boolean isDatabaseHealthy() {
        try {
            User.count();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Health implements Serializable {
        public String service;
        public String status;
        public String time;
    }
}
