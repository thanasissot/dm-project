//package sotiroglou.athanasios.microservices.resource;
//
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import sotiroglou.athanasios.microservices.entity.Links;
//
//import java.util.List;
//
//@Path("/links")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class LinksResource {
//
//    @Inject
//    LinksRepository linksRepository;
//
//    @GET
//    public List<Links> getAllLinks() {
//        return linksRepository.findAll();
//    }
//
//    @GET
//    @Path("/{id}")
//    public Response getLinksById(@PathParam("id") Long id) {
//        return linksRepository.findById(id)
//                .map(links -> Response.ok(links).build())
//                .orElse(Response.status(Response.Status.NOT_FOUND).build());
//    }
//
//    @POST
//    @Transactional
//    public Response createLinks(Links links) {
//        linksRepository.save(links);
//        return Response.status(Response.Status.CREATED).entity(links).build();
//    }
//
//    @PUT
//    @Path("/{id}")
//    @Transactional
//    public Response updateLinks(@PathParam("id") Long id, Links linkDetails) {
//        return linksRepository.findById(id)
//                .map(links -> {
//                    links.setSelf(linkDetails.getSelf());
//                    links.setUpdate(linkDetails.getUpdate());
//                    links.setDelete(linkDetails.getDelete());
//                    linksRepository.save(links);
//                    return Response.ok(links).build();
//                }).orElse(Response.status(Response.Status.NOT_FOUND).build());
//    }
//
//    @DELETE
//    @Path("/{id}")
//    @Transactional
//    public Response deleteLinks(@PathParam("id") Long id) {
//        linksRepository.deleteById(id);
//        return Response.status(Response.Status.OK).build();
//    }
//}