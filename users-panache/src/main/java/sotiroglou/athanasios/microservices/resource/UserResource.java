package sotiroglou.athanasios.microservices.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import sotiroglou.athanasios.microservices.entity.User;

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
        return User.findByIdOptional(new ObjectId(id))
                .map(user -> Response.ok(user).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response createUser(User user) {
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

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") String id) {
        User.deleteById(new ObjectId(id));
        return Response.status(Response.Status.OK).build();
    }
}