package sotiroglou.athanasios.microservices.auth.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;
import sotiroglou.athanasios.microservices.auth.dom.Address;
import sotiroglou.athanasios.microservices.auth.dom.AuthUser;
import sotiroglou.athanasios.microservices.auth.dom.Card;
import sotiroglou.athanasios.microservices.auth.repo.UserRepository;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserRepository userRepository;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/profile")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    public Response getUserProfile(@Context SecurityContext ctx) {
        String username = ctx.getUserPrincipal().getName();
        AuthUser user = userRepository.findByUsername(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Don't return password in the response
        user.setPassword(null);
        user.setSalt(null);

        return Response.ok(user).build();
    }

    @PUT
    @Path("/profile")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    @Transactional
    public Response updateUserProfile(@Context SecurityContext ctx, AuthUser updatedUser) {
        String username = ctx.getUserPrincipal().getName();
        AuthUser existingUser = userRepository.findByUsername(username);
        if (existingUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Only update allowed fields (not password, not roles)
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());

        userRepository.updateAuthUser(existingUser);

        // Don't return password in the response
        existingUser.setPassword(null);
        existingUser.setSalt(null);

        return Response.ok(existingUser).build();
    }

    @POST
    @Path("/addresses")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    @Transactional
    public Response addAddress(@Context SecurityContext ctx, Address address) {
        String username = ctx.getUserPrincipal().getName();
        AuthUser user = userRepository.findByUsername(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        address.setUser(user);
        userRepository.createAddress(address);

        return Response.status(Response.Status.CREATED).entity(address).build();
    }

    @GET
    @Path("/addresses")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    public Response getUserAddresses(@Context SecurityContext ctx) {
        String username = ctx.getUserPrincipal().getName();
        AuthUser user = userRepository.findByUsername(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Address> addresses = userRepository.findAddressesByUserId(user.getAuthUserId());
        return Response.ok(addresses).build();
    }

    @POST
    @Path("/cards")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    @Transactional
    public Response addCard(@Context SecurityContext ctx, Card card) {
        String username = ctx.getUserPrincipal().getName();
        AuthUser user = userRepository.findByUsername(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        card.setUser(user);
        userRepository.createCard(card);

        return Response.status(Response.Status.CREATED).entity(card).build();
    }

    @GET
    @Path("/cards")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    public Response getUserCards(@Context SecurityContext ctx) {
        String username = ctx.getUserPrincipal().getName();
        AuthUser user = userRepository.findByUsername(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Card> cards = userRepository.findCardsByUserId(user.getAuthUserId());
        return Response.ok(cards).build();
    }
}