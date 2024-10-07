package sotiroglou.athanasios.microservices.service;

import sotiroglou.athanasios.microservices.dto.AuthoriseResponse;

public interface PaymentService {
    AuthoriseResponse authorise(float amount);
}
