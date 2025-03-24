package sotiroglou.athanasios.microservices.auth;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sotiroglou.athanasios.microservices.auth.dom.Address;
import sotiroglou.athanasios.microservices.auth.dom.AuthRole;
import sotiroglou.athanasios.microservices.auth.dom.AuthUser;
import sotiroglou.athanasios.microservices.auth.dom.Card;
import sotiroglou.athanasios.microservices.auth.repo.AuthRepository;
import sotiroglou.athanasios.microservices.auth.repo.UserRepository;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

import static sotiroglou.athanasios.microservices.auth.dom.AuthRole.RoleName.*;

@Singleton
public class Startup {

    @Inject
    AuthRepository authRepository;

    @Inject
    UserRepository userRepository;

    @PersistenceContext
    EntityManager em;

    private final Random random = new Random();
    private final String[] FIRST_NAMES = {"John", "Maria", "Alex", "Sophia", "Michael", "Emma", "James", "Olivia", "Robert", "Emily"};
    private final String[] LAST_NAMES = {"Smith", "Johnson", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez", "Wilson"};
    private final String[] CITIES = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"};
    private final String[] STREETS = {"Main St", "Broadway", "Park Ave", "Oak St", "Maple Ave", "Cedar Ln", "Washington St", "Lincoln Ave", "Highland Dr", "Sunset Blvd"};
    private final String[] COUNTRIES = {"USA", "Canada", "UK", "Germany", "France", "Spain", "Italy", "Australia", "Japan", "Brazil"};

    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // Create roles first
        AuthRole authRoleSuperAdmin = createRole(SUPERADMIN);
        AuthRole authRoleAdmin = createRole(ADMIN);
        AuthRole authRoleUser = createRole(USER);

        // Create admin users with proper roles
        createAdminUsers(authRoleSuperAdmin, authRoleAdmin, authRoleUser);

        // Create 100 regular users
        createRandomUsers(100, authRoleUser);
    }

    private AuthRole createRole(AuthRole.RoleName roleName) {
        AuthRole role = new AuthRole();
        role.setRoleName(roleName);
        authRepository.createAuthRole(role);
        return role;
    }

    private void createAdminUsers(AuthRole superAdminRole, AuthRole adminRole, AuthRole userRole) {
        // SuperAdmin user
        AuthUser superAdmin = new AuthUser();
        superAdmin.setUsername("superAdmin");
        superAdmin.setPassword("superAdmin");
        superAdmin.setFirstName("Super");
        superAdmin.setLastName("Admin");
        superAdmin.setEmail("superadmin@example.com");
        superAdmin.setCustomerId(UUID.randomUUID().toString());
        superAdmin.setSalt(UUID.randomUUID().toString());

        HashSet<AuthRole> superAdminRoles = new HashSet<>();
        superAdminRoles.add(superAdminRole);
        superAdminRoles.add(adminRole);
        superAdminRoles.add(userRole);
        superAdmin.setAuthRoles(superAdminRoles);
        userRepository.createAuthUser(superAdmin);

        // Admin user
        AuthUser admin = new AuthUser();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setEmail("admin@example.com");
        admin.setCustomerId(UUID.randomUUID().toString());
        admin.setSalt(UUID.randomUUID().toString());

        HashSet<AuthRole> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);
        admin.setAuthRoles(adminRoles);
        userRepository.createAuthUser(admin);

        // Regular user
        AuthUser user = new AuthUser();
        user.setUsername("user");
        user.setPassword("user");
        user.setFirstName("Regular");
        user.setLastName("User");
        user.setEmail("user@example.com");
        user.setCustomerId(UUID.randomUUID().toString());
        user.setSalt(UUID.randomUUID().toString());

        HashSet<AuthRole> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setAuthRoles(userRoles);
        userRepository.createAuthUser(user);

        // Add sample addresses and cards for admin users
        addAddressesAndCards(superAdmin, 2, 2);
        addAddressesAndCards(admin, 2, 3);
        addAddressesAndCards(user, 3, 2);
    }

    private void createRandomUsers(int count, AuthRole userRole) {
        for (int i = 0; i < count; i++) {
            AuthUser randomUser = new AuthUser();

            String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

            randomUser.setFirstName(firstName);
            randomUser.setLastName(lastName);
            randomUser.setUsername(firstName.toLowerCase() + "." + lastName.toLowerCase() + random.nextInt(1000));
            randomUser.setPassword("password" + random.nextInt(1000));
            randomUser.setEmail(firstName.toLowerCase() + "." + lastName.toLowerCase() + "@example.com");
            randomUser.setCustomerId(UUID.randomUUID().toString());
            randomUser.setSalt(UUID.randomUUID().toString());

            HashSet<AuthRole> userRoles = new HashSet<>();
            userRoles.add(userRole);
            randomUser.setAuthRoles(userRoles);

            userRepository.createAuthUser(randomUser);

            // Add 2-3 addresses and 2-3 cards for each user
            int numAddresses = 2 + random.nextInt(2); // 2-3
            int numCards = 2 + random.nextInt(2);     // 2-3
            addAddressesAndCards(randomUser, numAddresses, numCards);
        }
    }

    private void addAddressesAndCards(AuthUser user, int numAddresses, int numCards) {
        // Add addresses
        for (int i = 0; i < numAddresses; i++) {
            Address address = new Address();
            address.setStreet(STREETS[random.nextInt(STREETS.length)]);
            address.setNumberS(10 + random.nextInt(1000));
            address.setCity(CITIES[random.nextInt(CITIES.length)]);
            address.setCountry(COUNTRIES[random.nextInt(COUNTRIES.length)]);
            address.setPostCode(String.format("%05d", random.nextInt(100000)));
            address.setUser(user);
            em.persist(address);
        }

        // Add cards
        for (int i = 0; i < numCards; i++) {
            Card card = new Card();
            // Generate a random but somewhat realistic card number
            String cardNumber = String.format("%04d-%04d-%04d-%04d",
                    1000 + random.nextInt(9000),
                    1000 + random.nextInt(9000),
                    1000 + random.nextInt(9000),
                    1000 + random.nextInt(9000));

            card.setCardNumber(cardNumber);
            card.setExpires(String.format("%02d/%d",
                    1 + random.nextInt(12),
                    2024 + random.nextInt(6)));
            card.setCcv(String.format("%03d", random.nextInt(1000)));
            card.setUser(user);
            em.persist(card);
        }
    }
}