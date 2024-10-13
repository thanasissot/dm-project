package sotiroglou.athanasios.microservices.resource;

import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.bson.types.ObjectId;
import sotiroglou.athanasios.microservices.ImageDirectoryProducer;
import sotiroglou.athanasios.microservices.entity.Sock;
import sotiroglou.athanasios.microservices.entity.SockTag;
import sotiroglou.athanasios.microservices.entity.Tag;
import sotiroglou.athanasios.microservices.repository.SockRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Path("/catalogue")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SockResource {

    Logger logger = Logger.getLogger(SockResource.class.getName());

    @Inject
    SockRepository sockRepository;

    @Inject
    ImageDirectoryProducer imageDirectoryProducer;

    @GET
    @Path("/all")
    public List<Sock> listSocks(
            @QueryParam("tags") List<String> tags,
            @QueryParam("order") String order,
            @QueryParam("pageNum") int pageNum,
            @QueryParam("pageSize") int pageSize
    ) {
        if (tags != null && !tags.isEmpty()) {
            List<Tag> tagList = Tag.find(new Document("name" , new Document("$in", tags))).list();
            List<ObjectId> tagIds = tagList.stream()
                    .map(tag -> tag.id)
                    .toList();
            if (tagIds.isEmpty()) {
                // No matching tags found, return an empty list
                return Collections.emptyList();
            }

            List<SockTag> sockTagList =
                    SockTag.find(new Document("tagId",
                            new Document("$in", tagIds)))
                            .list();
            List<ObjectId> sockIds = sockTagList.stream()
                    .map(sockTag -> sockTag.sockId)
                    .toList();

            Document queryDocument = new Document("_id", new Document("$in", sockIds));
            String sortOrder = order != null && !order.isEmpty() && !order.isBlank()
                    ? sortOrder = order
                    : "_id";  // Default sort by _id if no order is provided

            PanacheQuery<Sock> query = Sock.find(queryDocument.toJson(), Sort.by(sortOrder));
            return query.page(Page.of(pageNum, pageSize))
                    .list();
        } else {
            return Sock.findAll().page(pageNum, pageSize).list();
        }
    }

    @GET
    @Path("/size")
    public int countSocks(@QueryParam("tags") List<String> tags) {
        if (tags != null && !tags.isEmpty()) {
            PanacheQuery<Sock> query = Sock.find("tags", tags);
            return query.list().size();
        } else {
            return Sock.findAll().list().size();
        }
    }

    @GET
    @Path("/{id}")
    public Sock getSockById(@PathParam("id") String id) {
        Sock sock = sockRepository.findById(new ObjectId(id));
        if (sock == null) {
            throw new WebApplicationException("Sock with ID " + id + " not found.", Response.Status.NOT_FOUND);
        }
        return sock;
    }

    @GET
    @Path("/images/{imageName}")
    @Produces("image/jpeg")
    public Response getImage(@PathParam("imageName") String imageName) throws IOException {
        File imageFile = null;

        try {
            imageFile = new File(imageDirectoryProducer.getImagesDirectory(), imageName);

        } catch (Exception e) {
            // ignore
        }

        if (imageFile == null) {
            try {
                imageFile = new File(Objects.requireNonNull(
                        this.getClass().getClassLoader()
                                .getResource("images/" + imageName)).getFile());
            } catch (Exception e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }

        return Response.ok(Files.readAllBytes(imageFile.toPath()))
                .header("Content-Disposition", "inline; filename=\"" + imageName + "\"")
                .build();
    }

    @POST
    public Response createSock(Sock sock) {
        sockRepository.persist(sock);
        return Response.ok(sock).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateSock(@PathParam("id") String id, Sock updatedSock) {
        Sock sock = sockRepository.findById(new ObjectId(id));
        if (sock == null) {
            throw new WebApplicationException("Sock with ID " + id + " not found.", Response.Status.NOT_FOUND);
        }
        sock.name = updatedSock.name;
        sock.description = updatedSock.description;
        sock.price = updatedSock.price;
        sock.count = updatedSock.count;
        sock.imageUrls = updatedSock.imageUrls;
        sockRepository.update(sock);
        return Response.ok(sock).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSock(@PathParam("id") String id) {
        boolean deleted = sockRepository.deleteById(new ObjectId(id));
        if (!deleted) {
            throw new WebApplicationException("Sock with ID " + id + " not found.", Response.Status.NOT_FOUND);
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/search")
    public List<Sock> searchSocks(@QueryParam("name") String name, @QueryParam("description") String description) {
        if (name != null && !name.isEmpty()) {
            return sockRepository.find("name like ?1", "%" + name + "%").list();
        } else if (description != null && !description.isEmpty()) {
            return sockRepository.find("description like ?1", "%" + description + "%").list();
        } else {
            throw new WebApplicationException("Invalid search criteria", Response.Status.BAD_REQUEST);
        }
    }
}