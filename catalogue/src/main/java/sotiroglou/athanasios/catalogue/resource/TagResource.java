package sotiroglou.athanasios.catalogue.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import sotiroglou.athanasios.microservices.model.catalogue.Tag;

import java.util.List;
import java.util.stream.Collectors;

@Path("/tags")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TagResource {

    @GET
    @Path("")
    public List<String> listTags() {
        List<Tag> tags = Tag.findAll().list();
        return tags.stream().map(tag -> tag.name).collect(Collectors.toList());
    }
}
