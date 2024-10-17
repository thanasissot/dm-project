package sotiroglou.athanasios.microservices.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sotiroglou.athanasios.microservices.RequestUUIDHeaderFactory;
import sotiroglou.athanasios.microservices.Sock;

import java.util.concurrent.CompletionStage;

@Path("/")
@RegisterRestClient
@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
public interface CatalogueService {

    @GET
    @Path("{id}")
    CompletionStage<Sock> getByIdAsync(@PathParam("id") String id);
}
