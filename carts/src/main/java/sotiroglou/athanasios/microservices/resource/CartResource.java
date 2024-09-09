package sotiroglou.athanasios.microservices.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import sotiroglou.athanasios.microservices.entity.Cart;
import sotiroglou.athanasios.microservices.repository.CartRepository;

import java.util.List;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    @Inject
    CartRepository cartRepository;

    @GET
    @Path("/")
    public List<Cart> getAllCart() {
        return cartRepository.findAllCarts();
    }

    @POST
    public Response createCart(Cart cart) {
        cartRepository.persist(cart);
        return Response.status(Response.Status.CREATED).entity(cart).build();
    }

    @GET
    @Path("/{id}")
    public Cart getCart(@PathParam("id") String id) {
        return cartRepository.findById(new ObjectId(id));
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCart(@PathParam("id") String id) {
        cartRepository.deleteById(new ObjectId(id));
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    // Additional endpoints for updating carts, adding items to cart, etc.
}
