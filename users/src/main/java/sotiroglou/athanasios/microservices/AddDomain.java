package sotiroglou.athanasios.microservices;

import sotiroglou.athanasios.microservices.entity.Links;

public interface AddDomain {
    void populateLinks(Links links, String domain);
}
