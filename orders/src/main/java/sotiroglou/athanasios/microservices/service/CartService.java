package sotiroglou.athanasios.microservices.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sotiroglou.athanasios.microservices.RequestUUIDHeaderFactory;
import sotiroglou.athanasios.microservices.model.carts.Cart;

import java.util.concurrent.CompletionStage;

@Path("/")
@RegisterRestClient
@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
public interface CartService {

    @GET
    @Path("customer/{customerId}")
    CompletionStage<Cart> complete(
        @PathParam("customerId") String customerId
    );
}
