package sotiroglou.athanasios.microservices.commons;

import org.hibernate.validator.constraints.URL;

import java.net.URI;

public class NewOrderResource {
    @URL
    public URI customer;
    @URL
    public URI address;
    @URL
    public URI card;
    @URL
    public URI items;
}
