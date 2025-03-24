package sotiroglou.athanasios.microservices.carts;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CartService cartService;
    private final Random random = new Random();

    @Override
    @Transactional
    public void run(String... args) {
        createSampleCarts(100);
    }

    private void createSampleCarts(int numberOfCarts) {
        for (int i = 0; i < numberOfCarts; i++) {
            Cart cart = new Cart();
            cart.setCustomerId(UUID.randomUUID().toString());
            cartService.createCart(cart);

            // Add 2-3 items to each cart
            int numItems = 2 + random.nextInt(2); // 2-3 items
            addCartItems(cart, numItems);
        }
    }

    private void addCartItems(Cart cart, int numItems) {
        for (int i = 0; i < numItems; i++) {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProductId(UUID.randomUUID().toString());
            item.setQuantity(1 + random.nextInt(5)); // Random quantity between 1-5
            cartService.createCartItem(item);
        }
    }
}