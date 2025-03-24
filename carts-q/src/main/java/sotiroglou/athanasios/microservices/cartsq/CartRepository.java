package sotiroglou.athanasios.microservices.cartsq;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CartRepository implements PanacheRepository<Cart> {

    @Inject
    EntityManager em;

    @Transactional
    public List<Cart> findAllCarts() {
        return new ArrayList<>(listAll());
    }

    public Cart findCartById(Long id) {
        return Cart.findById(id);
    }

    public Cart findCartByCustomerId(String customerId) {
        return Cart.find("customerId", customerId).firstResult();
    }

    @Transactional
    public Cart createCart(Cart cart) {
        cart.persist();
        return cart;
    }

    @Transactional
    public void deleteCart(Cart cart) {
        cart.delete();
    }

    public List<CartItem> findAllCartItemsByCartId(Long cartId) {
        return CartItem.find("cart.id", cartId).list();
    }

    @Transactional
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.persist();
        return cartItem;
    }

    @Transactional
    public void deleteCartItem(CartItem cartItem) {
        cartItem.delete();
    }

    @Transactional
    public void deleteAllCartItemsByCartId(Long cartId) {
        CartItem.delete("cart.id", cartId);
    }
}