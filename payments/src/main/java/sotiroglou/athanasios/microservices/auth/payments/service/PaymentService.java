package sotiroglou.athanasios.microservices.auth.payments.service;

import sotiroglou.athanasios.microservices.auth.payments.payment.AuthoriseResponse;

public interface PaymentService {
    AuthoriseResponse authorise(float amount);
}
