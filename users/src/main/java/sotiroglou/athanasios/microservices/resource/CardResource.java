package sotiroglou.athanasios.microservices.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sotiroglou.athanasios.microservices.entity.Address;
import sotiroglou.athanasios.microservices.entity.Card;
import sotiroglou.athanasios.microservices.repository.AddressRepository;
import sotiroglou.athanasios.microservices.repository.CardRepository;

import java.util.List;

@Path("/cards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardResource {

    @Inject
    CardRepository cardRepository;

    @GET
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getCardById(@PathParam("id") Long id) {
        return cardRepository.findById(id)
                .map(card -> Response.ok(card).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response createCard(Card Card) {
        cardRepository.save(Card);
        return Response.status(Response.Status.CREATED).entity(Card).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateCard(@PathParam("id") Long id, Card cardDetails) {
        return cardRepository.findById(id)
                .map(card -> {
                    card.setCardNumber(cardDetails.getCardNumber());
                    card.setExpires(cardDetails.getExpires());
                    card.setCcv(cardDetails.getCcv());
                    cardRepository.save(card);
                    return Response.ok(card).build();
                }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCard(@PathParam("id") Long id) {
        cardRepository.deleteById(id);
        return Response.status(Response.Status.OK).build();
    }
}
