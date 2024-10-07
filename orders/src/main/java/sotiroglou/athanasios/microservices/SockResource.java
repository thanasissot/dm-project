package sotiroglou.athanasios.microservices;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Set;
import java.util.concurrent.CompletionStage;

@Path("/socks")
public class SockResource {

    @Inject
    @RestClient
    SockService sockService;

    @GET
    @Path("/{id}")
    public Sock id(@PathParam("id") String id) {
        Sock sock = sockService.getById(id);
        System.out.println(sock);
        return sock;
    }

    @GET
    @Path("/async/{id}")
    public CompletionStage<Sock> idAsync(@PathParam("id") String id) {
        CompletionStage<Sock> sock = sockService.getByIdAsync(id);
        System.out.println(sock);
        return sock;
    }
}
