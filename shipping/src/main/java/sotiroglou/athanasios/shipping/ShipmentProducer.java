package sotiroglou.athanasios.shipping;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import lombok.Setter;

import java.util.Random;

@ApplicationScoped
public class ShipmentProducer implements Runnable {

    @Inject
    ConnectionFactory connectionFactory;

    @Setter
    private Shipment shipment;

    private final Random random = new Random();

    @Override
    public void run() {
        try (JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {
            context.createProducer().send(context.createQueue("shipments"), shipment.toString() + " " + Integer.toString(random.nextInt(100)));
        }
    }
}
