package sotiroglou.athanasios.microservices;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Set;
import java.util.concurrent.CompletionStage;

@Path("/")
@RegisterRestClient
@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
public interface SockService {

    @GET
    @Path("{id}")
    Sock getById(@PathParam("id") String id);

    @GET
    @Path("{id}")
    CompletionStage<Sock> getByIdAsync(@PathParam("id") String id);

}
