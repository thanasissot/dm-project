package sotiroglou.athanasios.microservices.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sotiroglou.athanasios.microservices.RequestUUIDHeaderFactory;
import sotiroglou.athanasios.microservices.model.payment.AuthoriseRequest;
import sotiroglou.athanasios.microservices.model.users.User;

import java.util.concurrent.CompletionStage;

@Path("/")
@RegisterRestClient
@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
public interface PaymentsService {

    @POST
    @Path("/paymenthAuth")
    CompletionStage<User> getByIdAsync(
            @RequestBody AuthoriseRequest authoriseRequest
    );

}