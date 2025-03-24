package sotiroglou.athanasios.microservices.auth.tester.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sotiroglou.athanasios.microservices.auth.model.users.Address;
import sotiroglou.athanasios.microservices.auth.model.users.Card;
import sotiroglou.athanasios.microservices.auth.model.users.User;
import sotiroglou.athanasios.microservices.auth.tester.utils.RequestUUIDHeaderFactory;

import java.util.List;
import java.util.concurrent.CompletionStage;

@RegisterRestClient
@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
public interface UserService {

    @GET
    @Path("/users/customer/{id}")
    CompletionStage<User> getUserById(@PathParam("id") String id);

    @GET
    @Path("/addresses/customer/{id}")
    CompletionStage<List<Address>> getAddresses(@PathParam("id") String id);

    @GET
    @Path("/cards/customer/{id}")
    CompletionStage<List<Card>> getCards(@PathParam("id") String id);
}
