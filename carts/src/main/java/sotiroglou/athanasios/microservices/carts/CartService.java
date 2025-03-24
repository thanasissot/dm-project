package sotiroglou.athanasios.microservices.carts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    public Cart findCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart findCartByCustomerId(String customerId) {
        return cartRepository.findByCustomerId(customerId);
    }

    @Transactional
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

    public List<CartItem> findAllCartItemsByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Transactional
    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Transactional
    public void deleteCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    @Transactional
    public void deleteAllCartItemsByCartId(Long cartId) {
        cartItemRepository.deleteAllByCartId(cartId);
    }

    @Transactional
    public CartItem updateCartItemQuantity(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
}