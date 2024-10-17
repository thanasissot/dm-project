package sotiroglou.athanasios.microservices.orders.resource;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import sotiroglou.athanasios.microservices.orders.exception.InvalidOrderException;
import sotiroglou.athanasios.microservices.orders.exception.NewOrderResource;
import sotiroglou.athanasios.microservices.entity.CustomerOrder;

import java.io.IOException;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Value(value = "${http.timeout:5}")
    private long timeout;

    @POST
    @Path("/new")
    public CustomerOrder newOrder(@RequestBody NewOrderResource item) {

        if (item.address == null || item.customer == null || item.card == null || item.items == null) {
            throw new InvalidOrderException("Invalid order request. Order requires customer, address, card and items.");
        }


        return null;
    }

}