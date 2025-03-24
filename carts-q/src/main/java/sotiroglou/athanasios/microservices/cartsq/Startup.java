package sotiroglou.athanasios.microservices.cartsq;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import sotiroglou.athanasios.microservices.cartsq.Cart;
import sotiroglou.athanasios.microservices.cartsq.CartItem;
import sotiroglou.athanasios.microservices.cartsq.CartRepository;

import java.util.Random;
import java.util.UUID;

@Singleton
public class Startup {

    @Inject
    CartRepository cartRepository;

    private final Random random = new Random();

    @Transactional
    public void loadData(@Observes StartupEvent evt) {
        // Create sample carts and cart items
        createSampleCarts();
    }

    private void createSampleCarts() {
        // Create two sample carts with different customer IDs
        Cart cart1 = new Cart();
        cart1.setCustomerId(UUID.randomUUID().toString());
        cartRepository.createCart(cart1);

        Cart cart2 = new Cart();
        cart2.setCustomerId(UUID.randomUUID().toString());
        cartRepository.createCart(cart2);

        // Create sample cart items for cart1
        addCartItems(cart1, 2);

        // Create sample cart items for cart2
        addCartItems(cart2, 3);

        // Create more random carts (optional)
        for (int i = 0; i < 10; i++) {
            Cart randomCart = new Cart();
            randomCart.setCustomerId(UUID.randomUUID().toString());
            cartRepository.createCart(randomCart);

            // Add 1-5 items to each cart
            int numItems = 1 + random.nextInt(5);
            addCartItems(randomCart, numItems);
        }
    }

    private void addCartItems(Cart cart, int numItems) {
        for (int i = 0; i < numItems; i++) {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProductId(UUID.randomUUID().toString());
            item.setQuantity(1 + random.nextInt(10)); // Random quantity between 1-10
            cartRepository.createCartItem(item);
        }
    }
}