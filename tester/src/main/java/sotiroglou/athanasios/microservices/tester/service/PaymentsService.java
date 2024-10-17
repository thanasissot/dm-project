package sotiroglou.athanasios.microservices.tester.service;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sotiroglou.athanasios.microservices.model.payment.AuthoriseRequest;
import sotiroglou.athanasios.microservices.model.payment.AuthoriseResponse;
import sotiroglou.athanasios.microservices.tester.utils.RequestUUIDHeaderFactory;

import java.util.concurrent.CompletionStage;

@RegisterRestClient
@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
public interface PaymentsService {

    @POST
    @Path("/payment")
    CompletionStage<AuthoriseResponse> getPaymentAuthorization(
            @RequestBody AuthoriseRequest authoriseRequest
    );

}