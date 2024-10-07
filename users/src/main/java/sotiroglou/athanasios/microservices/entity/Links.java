package sotiroglou.athanasios.microservices.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Links {

    @ElementCollection
    @MapKeyColumn(name = "rel")
    private Map<String, String> links = new HashMap<>();

    public Map<String, String> getLinks() {
        return links;
    }

    public void addLink(String entity, String domain, String entityMap, String id) {
        String link = String.format("http://%s/%s/%s", domain, entityMap, id);
        links.put(entity, link);
        links.put("self", link);  // Add the self link
    }

    public void addAttrLink(String attribute, String relatedEntity, String domain, String relatedEntityMap, String id) {
        String link = String.format("http://%s/%s/%s/%s", domain, relatedEntityMap, id, attribute);
        links.put(attribute, link);
    }

    public void addCustomerLinks(String domain, String customerId) {
        addLink("customer", domain, "customers", customerId);
        addAttrLink("address", "customer", domain, "customers", customerId);
        addAttrLink("card", "customer", domain, "customers", customerId);
    }

    public void addAddressLink(String domain, String addressId) {
        addLink("address", domain, "addresses", addressId);
    }

    public void addCardLink(String domain, String cardId) {
        addLink("card", domain, "cards", cardId);
    }
}