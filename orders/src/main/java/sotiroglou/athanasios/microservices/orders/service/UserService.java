package sotiroglou.athanasios.microservices.orders.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sotiroglou.athanasios.microservices.orders.utils.RequestUUIDHeaderFactory;
import sotiroglou.athanasios.microservices.model.users.User;

import java.util.concurrent.CompletionStage;

@RegisterRestClient
@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
public interface UserService {

    @GET
    @Path("/{id}")
    CompletionStage<User> getByIdAsync(@PathParam("id") String id);
}
