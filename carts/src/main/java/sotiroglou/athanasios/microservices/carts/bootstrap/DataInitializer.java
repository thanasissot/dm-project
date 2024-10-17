//package sotiroglou.athanasios.microservices.carts.bootstrap;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.enterprise.event.Observes;
//import jakarta.enterprise.event.Startup;
//import jakarta.transaction.Transactional;
//import org.bson.types.ObjectId;
//import sotiroglou.athanasios.microservices.model.carts.Cart;
//import sotiroglou.athanasios.microservices.model.carts.CartItem;
//
//import java.util.Set;
//
//@ApplicationScoped
//public class DataInitializer {
//
//    private void forceEagerInitialization(@Observes Startup startup) {
//    }
//
////    @PostConstruct
//    public void initializeData() {
//            // Create carts
//            Cart cart1 = new Cart();
//            cart1.customerId = new ObjectId("111111111111111111111111");
//            Cart cart2 = new Cart();
//            cart2.customerId = new ObjectId("222222222222222222222222");
//
//            // Save the carts
//            cart1.persist();
//            cart2.persist();
//
//            // Create CartItem instances
//            CartItem item1 = new CartItem();
//            item1.quantity = 2;
//            item1.productId = new ObjectId("6d62d909f957430e8689b512");
//            item1.cartId = cart1.id;
//            item1.id = new ObjectId();
//
//            CartItem item2 = new CartItem();
//            item2.quantity = 1;
//            item2.productId = new ObjectId("a0a4f044b040410d8ead4de0");
//            item2.id = new ObjectId();
//            item2.cartId = cart1.id;
//
//            CartItem item3 = new CartItem();
//            item3.quantity = 3;
//            item3.productId = new ObjectId("808a2de11aaa4c25a9b96612");
//            item3.id = new ObjectId();
//            item3.cartId = cart2.id;
//
//            // Associate CartItems with the corresponding carts
//            cart1.cartItems = Set.of(item1, item2);
//            cart2.cartItems = Set.of(item3);
//
//            // Save the carts
//            Cart.persist(cart1);
//            Cart.persist(cart2);
//
//            // Persist items manually as there are no automatic relationships in MongoDB
//            item1.persist();
//            item2.persist();
//            item3.persist();
//        }
//}
