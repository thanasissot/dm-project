package sotiroglou.athanasios.microservices.bootstrap;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.transaction.Transactional;
import org.bson.types.ObjectId;
import sotiroglou.athanasios.microservices.entity.Address;
import sotiroglou.athanasios.microservices.entity.Card;
import sotiroglou.athanasios.microservices.entity.User;

@ApplicationScoped
public class DataInitializer {

    private void forceEagerInitialization(@Observes Startup startup) {
    }

    @PostConstruct
    @Transactional
    public void initializeData() {
        System.out.println("Initializing data...");
        String domain = "example.com";  // Replace with the actual domain

        // Create First User
        User user1 = new User();
        user1.setFirstName("John Doe");
        user1.setLastName("John Doe");
        user1.setEmail("john.doe@example.com");
        user1.setUsername("john.doe");
        user1.setPassword("password");
        user1.setSalt("salt");
        user1.setCustomerId(new ObjectId());
        user1.persist();

        // Create First User Addresses
        Address address1User1 = new Address();
        address1User1.setNumber(123L);
        address1User1.setStreet("Main Street");
        address1User1.setCity("Springfield");
        address1User1.setPostCode("12345");
        address1User1.setCountry("USA");
        address1User1.setUserId(user1.id);
        address1User1.persist();

        Address address2User1 = new Address();
        address2User1.setNumber(123L);
        address2User1.setStreet("Elm Street");
        address2User1.setCity("Springfield");
        address2User1.setPostCode("12345");
        address2User1.setCountry("USA");
        address2User1.setUserId(user1.id);
        address2User1.persist();

        // Create First User Payment Cards
        Card card1User1 = new Card();
        card1User1.setCardNumber("1111-2222-3333-4444");
        card1User1.setExpires("12/24");
        card1User1.setCcv("123");
        card1User1.setUserId(user1.id);
        card1User1.persist();

        Card card2User1 = new Card();
        card2User1.setCardNumber("5555-6666-7777-8888");
        card2User1.setExpires("06/25");
        card2User1.setCcv("456");
        card2User1.setUserId(user1.id);
        card2User1.persist();

        // Create Second User
        User user2 = new User();
        user2.setFirstName("Jane Smith");
        user2.setLastName("Jane Smith");
        user2.setEmail("jane.smith@example.com");
        user2.setUsername("jane.smith");
        user2.setPassword("password");
        user2.setSalt("salt2");
        user2.setCustomerId(new ObjectId());
        user2.persist();

        // Create Second User Addresses
        Address address1User2 = new Address();
        address1User2.setNumber(123L);
        address1User2.setStreet("Maple Street");
        address1User2.setCity("Metropolis");
        address1User2.setPostCode("67890");
        address1User2.setCountry("USA");
        address1User2.setUserId(user2.id);
        address1User2.persist();

        Address address2User2 = new Address();
        address2User2.setNumber(123L);
        address2User2.setStreet("Oak Street");
        address2User2.setCity("Metropolis");
        address2User2.setPostCode("67890");
        address2User2.setCountry("USA");
        address2User2.setUserId(user2.id);
        address2User2.persist();

        // Create Second User Payment Cards
        Card card1User2 = new Card();
        card1User2.setCardNumber("9999-0000-1111-2222");
        card1User2.setExpires("09/26");
        card1User2.setCcv("789");
        card1User2.setUserId(user2.id);
        card1User2.persist();

        Card card2User2 = new Card();
        card2User2.setCardNumber("3333-4444-5555-6666");
        card2User2.setExpires("01/27");
        card2User2.setCcv("012");
        card2User2.setUserId(user2.id);
        card2User2.persist();

        System.out.println("Sample data loaded successfully.");
    }

}
