package sotiroglou.athanasios.microservices.auth.payments.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sotiroglou.athanasios.microservices.auth.payments.payment.AuthoriseRequest;
import sotiroglou.athanasios.microservices.auth.payments.payment.AuthoriseResponse;
import sotiroglou.athanasios.microservices.auth.payments.service.PaymentService;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Inject
    PaymentService paymentService;

    @POST
    @Path("/payment")
    public Response authorisePayment(AuthoriseRequest request) {
        AuthoriseResponse response = paymentService.authorise(request.getAmount());

        return Response.status(Response.Status.OK).entity(response).build();
    }

}
