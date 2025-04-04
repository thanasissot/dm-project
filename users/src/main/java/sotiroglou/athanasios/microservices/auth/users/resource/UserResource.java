package sotiroglou.athanasios.microservices.users.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import sotiroglou.athanasios.microservices.users.users.Address;
import sotiroglou.athanasios.microservices.users.users.Card;
import sotiroglou.athanasios.microservices.users.users.User;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {


    @GET
    public List<User> getAllUsers() {
        return User.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") String id) {
        User user = User.findById(new ObjectId(id));
        List<Address> addresses = Address.find("userId", user.id).list();
        List<Card> cards = Card.find("userId", user.id).list();
        return Response.ok(user).build();

    }

    @GET
    @Path("/customer/{customerId}")
    public Response getUserByCustomerId(@PathParam("customerId") String customerId) {
        User user = User.find("customerId", new ObjectId(customerId)).singleResult();
        List<Address> addresses = Address.find("userId", user.id).list();
        List<Card> cards = Card.find("userId", user.id).list();
        return Response.ok(user).build();

    }

    @POST
    public Response createUser(@RequestBody User user) {
        user.customerId = new ObjectId();
        user.persist();
        // The `id` is generated and available after persistence
        if (user.id != null) {
            return Response.status(Response.Status.CREATED)
                    .entity(user)
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("User could not be created")
                    .build();
        }
    }

    @PUT
    public Response updateUser(@RequestBody User user) {
        user.persistOrUpdate();
        // The `id` is generated and available after persistence
        if (user.id != null) {
            return Response.status(Response.Status.CREATED)
                    .entity(user)
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("User could not be created")
                    .build();
        }
    }


    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        User.deleteById(new ObjectId(id));
        return Response.status(Response.Status.OK).build();
    }
}