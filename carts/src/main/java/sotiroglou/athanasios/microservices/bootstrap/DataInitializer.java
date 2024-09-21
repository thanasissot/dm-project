package sotiroglou.athanasios.microservices.bootstrap;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import sotiroglou.athanasios.microservices.entity.Cart;
import sotiroglou.athanasios.microservices.entity.CartItem;
import sotiroglou.athanasios.microservices.repository.CartRepository;

import java.util.Arrays;
import java.util.Set;

@ApplicationScoped
public class DataInitializer {

    @Inject
    CartRepository cartRepository;

    private void forceEagerInitialization(@Observes Startup startup) {
    }

    @PostConstruct
    @Transactional
    public void initializeData() {
        System.out.println("Initializing data... count=" + cartRepository.count());
        if (cartRepository.count() == 0) {
            Cart cart1 = Cart.builder()
                    .customerId(101L)
                    .build();

            Cart cart2 = Cart.builder()
                    .customerId(102L)
                    .build();

            // Create CartItem instances using builder and associate them with carts
            CartItem item1 = CartItem.builder()
                    .quantity(2)
                    .unitPrice(29.99)
                    .productId(1001L)
                    .cart(cart1)
                    .build();

            CartItem item2 = CartItem.builder()
                    .quantity(1)
                    .unitPrice(59.99)
                    .productId(1002L)
                    .cart(cart1)
                    .build();

            CartItem item3 = CartItem.builder()
                    .quantity(3)
                    .unitPrice(15.99)
                    .productId(1003L)
                    .cart(cart2)
                    .build();

            // Associate CartItems with the corresponding carts
            cart1.setCartItems(Set.of(item1, item2));
            cart2.setCartItems(Set.of(item3));

            cartRepository.save(cart1);
            cartRepository.save(cart2);
        }
    }

}
