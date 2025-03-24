package sotiroglou.athanasios.microservices.auth.orders.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sotiroglou.athanasios.microservices.auth.orders.utils.RequestUUIDHeaderFactory;
import sotiroglou.athanasios.microservices.auth.model.carts.Cart;

import java.util.concurrent.CompletionStage;

@Path("/")
@RegisterRestClient
@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
public interface CartService {

    @GET
    @Path("customer/{customerId}")
    CompletionStage<Cart> getCartByCustomerId(
            @PathParam("customerId") String customerId
    );
}
