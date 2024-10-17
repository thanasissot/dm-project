package sotiroglou.athanasios.payments.service;


import sotiroglou.athanasios.microservices.model.payment.AuthoriseResponse;

public interface PaymentService {
    AuthoriseResponse authorise(float amount);
}
