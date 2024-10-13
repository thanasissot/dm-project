package sotiroglou.athanasios.microservices.bootstrap;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.transaction.Transactional;
import org.bson.types.ObjectId;
import sotiroglou.athanasios.microservices.entity.Cart;
import sotiroglou.athanasios.microservices.entity.CartItem;

import java.util.Set;

@ApplicationScoped
public class DataInitializer {

    private void forceEagerInitialization(@Observes Startup startup) {
    }

    @PostConstruct
    @Transactional
    public void initializeData() {
        if (Cart.count() == 0) {
            System.out.println("Initializing data... count=" + Cart.count());

            // Create carts
            Cart cart1 = new Cart();
            Cart cart2 = new Cart();

            // Save the carts
            cart1.persist();
            cart2.persist();

            // Create CartItem instances
            CartItem item1 = new CartItem();
            item1.quantity = 2;
            item1.productId = new ObjectId();
            item1.cartId = cart1.id;
            item1.id = new ObjectId();

            CartItem item2 = new CartItem();
            item2.quantity = 1;
            item2.productId = new ObjectId();
            item2.id = new ObjectId();
            item2.cartId = cart1.id;

            CartItem item3 = new CartItem();
            item3.quantity = 3;
            item3.productId = new ObjectId();
            item3.id = new ObjectId();
            item3.cartId = cart2.id;

            // Associate CartItems with the corresponding carts
            cart1.cartItems = Set.of(item1, item2);
            cart2.cartItems = Set.of(item3);

            // Save the carts
            Cart.persist(cart1);
            Cart.persist(cart2);

            // Persist items manually as there are no automatic relationships in MongoDB
            item1.persist();
            item2.persist();
            item3.persist();
        }
    }
}
