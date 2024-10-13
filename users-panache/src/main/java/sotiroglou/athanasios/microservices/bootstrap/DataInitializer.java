//package sotiroglou.athanasios.microservices.bootstrap;
//
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.enterprise.event.Observes;
//import jakarta.enterprise.event.Startup;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
//import sotiroglou.athanasios.microservices.entity.Address;
//import sotiroglou.athanasios.microservices.entity.Card;
//import sotiroglou.athanasios.microservices.entity.User;
//import sotiroglou.athanasios.microservices.repository.AddressRepository;
//import sotiroglou.athanasios.microservices.repository.CardRepository;
//import sotiroglou.athanasios.microservices.repository.UserRepository;
//
//@ApplicationScoped
//public class DataInitializer {
//
//    @Inject
//    UserRepository userRepository;
//
//    @Inject
//    AddressRepository addressRepository;
//
//    @Inject
//    CardRepository cardRepository;
//
//    private void forceEagerInitialization(@Observes Startup startup) {
//    }
//
////    @PostConstruct
//    @Transactional
//    public void initializeData() {
//        System.out.println("Initializing data...");
//        String domain = "example.com";  // Replace with the actual domain
//
//        // Create First User
//        User user1 = new User();
//        user1.setFirstName("John Doe");
//        user1.setLastName("John Doe");
//        user1.setEmail("john.doe@example.com");
//        user1.setUsername("john.doe");
//        user1.setPassword("password");
//        user1.setSalt("salt");
//        user1.setCustomerId(1L);
//        userRepository.save(user1);
//        user1.populateLinks(user1.getLinks(), domain);  // Populate HATEOAS links for the user
//        userRepository.save(user1);
//
//        // Create First User Addresses
//        Address address1User1 = new Address();
//        address1User1.setNumber(123L);
//        address1User1.setStreet("Main Street");
//        address1User1.setCity("Springfield");
//        address1User1.setPostCode("12345");
//        address1User1.setCountry("USA");
//        address1User1.setUser(user1);
//        addressRepository.save(address1User1);
//        address1User1.populateLinks(address1User1.getLinks(), domain);  // Populate HATEOAS links for the address
//        addressRepository.save(address1User1);  // Update address with links
//
//        Address address2User1 = new Address();
//        address2User1.setNumber(123L);
//        address2User1.setStreet("Elm Street");
//        address2User1.setCity("Springfield");
//        address2User1.setPostCode("12345");
//        address2User1.setCountry("USA");
//        address2User1.setUser(user1);
//        addressRepository.save(address2User1);
//        address2User1.populateLinks(address2User1.getLinks(), domain);  // Populate HATEOAS links for the address
//        addressRepository.save(address2User1);  // Update address with links
//
//        // Create First User Payment Cards
//        Card card1User1 = new Card();
//        card1User1.setCardNumber("1111-2222-3333-4444");
//        card1User1.setExpires("12/24");
//        card1User1.setCcv("123");
//        card1User1.setUser(user1);
//        cardRepository.save(card1User1);
//        card1User1.populateLinks(card1User1.getLinks(), domain);
//        cardRepository.save(card1User1);
//
//        Card card2User1 = new Card();
//        card2User1.setCardNumber("5555-6666-7777-8888");
//        card2User1.setExpires("06/25");
//        card2User1.setCcv("456");
//        card2User1.setUser(user1);
//        cardRepository.save(card2User1);
//        card2User1.populateLinks(card2User1.getLinks(), domain);
//        cardRepository.save(card2User1);
//
//        // Create Second User
//        User user2 = new User();
//        user2.setFirstName("Jane Smith");
//        user2.setLastName("Jane Smith");
//        user2.setEmail("jane.smith@example.com");
//        user2.setUsername("jane.smith");
//        user2.setPassword("password");
//        user2.setSalt("salt2");
//        user2.setCustomerId(2L);
//        userRepository.save(user2);
//        user2.populateLinks(user2.getLinks(), domain);  // Populate HATEOAS links for the address
//        userRepository.save(user2);  // Update address with links
//
//        // Create Second User Addresses
//        Address address1User2 = new Address();
//        address1User2.setNumber(123L);
//        address1User2.setStreet("Maple Street");
//        address1User2.setCity("Metropolis");
//        address1User2.setPostCode("67890");
//        address1User2.setCountry("USA");
//        address1User2.setUser(user2);
//        addressRepository.save(address1User2);
//        address1User2.populateLinks(address1User2.getLinks(), domain);  // Populate HATEOAS links for the address
//        addressRepository.save(address1User2);  // Update address with links
//
//        Address address2User2 = new Address();
//        address2User2.setNumber(123L);
//        address2User2.setStreet("Oak Street");
//        address2User2.setCity("Metropolis");
//        address2User2.setPostCode("67890");
//        address2User2.setCountry("USA");
//        address2User2.setUser(user2);
//        addressRepository.save(address2User2);
//        address2User2.populateLinks(address2User2.getLinks(), domain);  // Populate HATEOAS links for the address
//        addressRepository.save(address2User2);  // Update address with links
//
//        // Create Second User Payment Cards
//        Card card1User2 = new Card();
//        card1User2.setCardNumber("9999-0000-1111-2222");
//        card1User2.setExpires("09/26");
//        card1User2.setCcv("789");
//        card1User2.setUser(user2);
//        cardRepository.save(card1User2);
//        card1User2.populateLinks(card1User2.getLinks(), domain);
//        cardRepository.save(card1User2);
//
//        Card card2User2 = new Card();
//        card2User2.setCardNumber("3333-4444-5555-6666");
//        card2User2.setExpires("01/27");
//        card2User2.setCcv("012");
//        card2User2.setUser(user2);
//        cardRepository.save(card2User2);
//        card1User2.populateLinks(card1User2.getLinks(), domain);
//        cardRepository.save(card1User2);
//
//        System.out.println("Sample data loaded successfully.");
//    }
//
//}
