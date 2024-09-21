package sotiroglou.athanasios.microservices;

import sotiroglou.athanasios.microservices.entity.Cart;
import sotiroglou.athanasios.microservices.entity.CartItem;

import java.util.Set;

public class Utils {

    public static void assignCart(Cart cart, Set<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            cartItem.setCart(cart);
        }
    }
}
