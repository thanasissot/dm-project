package sotiroglou.athanasios.microservices.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sotiroglou.athanasios.microservices.dto.AuthoriseRequest;
import sotiroglou.athanasios.microservices.dto.AuthoriseResponse;
import sotiroglou.athanasios.microservices.service.PaymentService;

@Path("/paymentAuth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Inject
    PaymentService paymentService;

    @POST
    public Response authorisePayment(AuthoriseRequest request) {
        AuthoriseResponse response = paymentService.authorise(request.getAmount());

        if (!response.isAuthorised()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }

        return Response.ok(response).build();
    }

}
