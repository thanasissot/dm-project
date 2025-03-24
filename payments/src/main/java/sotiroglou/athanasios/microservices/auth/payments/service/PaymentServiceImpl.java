package sotiroglou.athanasios.microservices.auth.payments.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.enterprise.context.ApplicationScoped;
import sotiroglou.athanasios.microservices.auth.payments.payment.AuthoriseResponse;

@ApplicationScoped
public class PaymentServiceImpl implements PaymentService {

    // Read the value from application.properties or use 100.0 as a default
    @ConfigProperty(name = "payment.decline-over-amount", defaultValue = "100.0")
    float declineOverAmount;

    @Override
    public AuthoriseResponse authorise(float amount) {
        if (amount <= 0) {
            return new AuthoriseResponse(false, "Invalid payment amount");
        }

        if (amount <= declineOverAmount) {
            return new AuthoriseResponse(true, "Payment authorised");
        } else {
            String message = String.format("Payment declined: amount exceeds %.2f", declineOverAmount);
            return new AuthoriseResponse(false, message);
        }
    }
}
