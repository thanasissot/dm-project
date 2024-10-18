package sotiroglou.athanasios.shipping;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/shipment")
public class ShipmentResource {

    @Inject
    ShipmentProducer producer;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String createShupment(
            @RequestBody final Shipment shipment
    ) {
        producer.setShipment(shipment);
        producer.run();
        return shipment.getId();
    }
}
