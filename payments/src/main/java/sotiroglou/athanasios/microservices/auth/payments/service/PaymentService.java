package sotiroglou.athanasios.microservices.payments.service;

import sotiroglou.athanasios.microservices.payments.payment.AuthoriseResponse;

public interface PaymentService {
    AuthoriseResponse authorise(float amount);
}
