package sotiroglou.athanasios.microservices.auth.users.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import sotiroglou.athanasios.microservices.auth.users.users.Address;

import java.util.List;

@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {

    @GET
    public List<Address> getAllAddresses() {
        return Address.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Response getAddressById(@PathParam("id") String id) {
        Address address = Address.findById(new ObjectId(id));
        return Response.ok(address).build();
    }

    @GET
    @Path("/customer/{customerId}")
    public Response getAddressByCustomerId(@PathParam("customerId") String customerId) {
        List<Address> addresses = Address.find(new Document("userId", new ObjectId(customerId))).list();
        return Response.ok(addresses).build();
    }

    @POST
    public Response createAddress(
            @RequestBody Address address) {
        address.persist();
        return Response.status(Response.Status.CREATED).entity(address).build();
    }

    @PUT
    public Response updateAddress(
            @RequestBody Address addressDetails) {
        addressDetails.persistOrUpdate();
        return Response.status(Response.Status.CREATED).entity(addressDetails).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAddress(@PathParam("id") String id) {
        ObjectId addressId = new ObjectId(id);
        Address.deleteById(addressId);
        return Response.status(Response.Status.OK).build();
    }
}
