//package sotiroglou.athanasios.microservices.resource;
//
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import sotiroglou.athanasios.microservices.entity.Address;
//import sotiroglou.athanasios.microservices.repository.AddressRepository;
//
//import java.util.List;
//
//@Path("/addresses")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class AddressResource {
//
//    @Inject
//    AddressRepository addressRepository;
//
//    @GET
//    public List<Address> getAllAddresses() {
//        return addressRepository.findAll();
//    }
//
//    @GET
//    @Path("/{id}")
//    public Response getAddressById(@PathParam("id") Long id) {
//        return addressRepository.findById(id)
//                .map(address -> Response.ok(address).build())
//                .orElse(Response.status(Response.Status.NOT_FOUND).build());
//    }
//
//    @POST
//    @Transactional
//    public Response createAddress(Address address) {
//        addressRepository.save(address);
//        return Response.status(Response.Status.CREATED).entity(address).build();
//    }
//
//    @PUT
//    @Path("/{id}")
//    @Transactional
//    public Response updateAddress(@PathParam("id") Long id, Address addressDetails) {
//        return addressRepository.findById(id)
//                .map(address -> {
//                    address.setNumber(addressDetails.getNumber());
//                    address.setStreet(addressDetails.getStreet());
//                    address.setCity(addressDetails.getCity());
//                    address.setPostCode(addressDetails.getPostCode());
//                    address.setCountry(addressDetails.getCountry());
//                    addressRepository.save(address);
//                    return Response.ok(address).build();
//                }).orElse(Response.status(Response.Status.NOT_FOUND).build());
//    }
//
//    @DELETE
//    @Path("/{id}")
//    @Transactional
//    public Response deleteAddress(@PathParam("id") Long id) {
//        addressRepository.deleteById(id);
//        return Response.status(Response.Status.OK).build();
//    }
//}
