package sotiroglou.athanasios.catalogue.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import sotiroglou.athanasios.microservices.model.catalogue.SockTag;

@Path("/catalogue/socks/{sockId}/tags")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SockTagResource {

    @POST
    @Path("/{tagId}")
    public Response addTagToSock(@PathParam("sockId") String sockId, @PathParam("tagId") String tagId) {
        SockTag sockTag = new SockTag();
        sockTag.sockId = new ObjectId(sockId);
        sockTag.tagId = new ObjectId(tagId);
        SockTag.persist(sockTag);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{tagId}")
    public Response removeTagFromSock(@PathParam("sockId") String sockId, @PathParam("tagId") String tagId) {
        SockTag sockTag = SockTag.find("sockId = ?1 and tagId = ?2", new ObjectId(sockId), new ObjectId(tagId)).firstResult();
        if (sockTag == null) {
            throw new WebApplicationException("Relation between sock and tag not found.", Response.Status.NOT_FOUND);
        }
        SockTag.deleteById(sockTag.id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
