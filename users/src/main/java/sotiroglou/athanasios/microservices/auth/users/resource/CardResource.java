package sotiroglou.athanasios.microservices.auth.users.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import sotiroglou.athanasios.microservices.auth.users.users.Card;

import java.util.List;

@Path("/cards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardResource {

    @GET
    public List<Card> getAllCards() {
        return Card.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Response getCardById(@PathParam("id") String id) {
        Card card = Card.findById(new ObjectId(id));
        return Response.ok(card).build();
    }

    @GET
    @Path("/customer/{customerId}")
    public Response getCardByCustomerId(@PathParam("customerId") String customerId) {
        List<Card> cards = Card.find(new Document("userId", new ObjectId(customerId))).list();
        return Response.ok(cards).build();
    }

    @POST
    public Response createCard(@RequestBody Card card) {
        card.persist();
        return Response.status(Response.Status.CREATED).entity(card).build();
    }

    @PUT
    public Response updateCard(@RequestBody Card cardDetails) {
        cardDetails.persistOrUpdate();
        return Response.status(Response.Status.CREATED).entity(cardDetails).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCard(@PathParam("id") String id) {
        ObjectId cardId = new ObjectId(id);
        Card.deleteById(cardId);
        return Response.status(Response.Status.OK).build();
    }
}
