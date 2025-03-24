package sotiroglou.athanasios.microservices.carts;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sotiroglou.athanasios.microservices.carts.dto.CartItemDto;
import sotiroglou.athanasios.microservices.carts.dto.CartQueryDto;
import sotiroglou.athanasios.microservices.carts.dto.CartResponseDto;
import sotiroglou.athanasios.microservices.carts.dto.CreateCartDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
//    public List<CartResponseDto> getAllCarts() {
//        List<Cart> carts = cartService.findAllCarts();
//
//        return carts.stream()
//                .map(cart -> {
//                    List<CartItem> items = cartService.findAllCartItemsByCartId(cart.getId());
//                    return CartResponseDto.builder()
//                            .customerId(cart.getCustomerId())
//                            .cartId(String.valueOf(cart.getId()))
//                            .cartItems(new HashSet<>(items))
//                            .build();
//                })
//                .collect(Collectors.toList());
//    }
    public List<CartResponseDto> getAllCarts() {
        List<Cart> carts = cartService.findAllCarts();

        return carts.stream()
                .map(cart -> {
                    // Don't reload cart items if they're already loaded
                    Set<CartItem> items = new HashSet<>(cartService.findAllCartItemsByCartId(cart.getId()));
                    return CartResponseDto.builder()
                            .customerId(cart.getCustomerId())
                            .cartId(String.valueOf(cart.getId()))
                            .cartItems(items)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Cart> createCart(@RequestBody CreateCartDto createCartDto) {
        Cart cart = new Cart();
        cart.setCustomerId(createCartDto.getCustomerId());
        cartService.createCart(cart);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    public ResponseEntity<CartResponseDto> getCartByCustomer(@PathVariable("id") String id) {
        Cart cart = cartService.findCartByCustomerId(id);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        List<CartItem> items = cartService.findAllCartItemsByCartId(cart.getId());
        CartResponseDto responseDto = CartResponseDto.builder()
                .customerId(cart.getCustomerId())
                .cartId(String.valueOf(cart.getId()))
                .cartItems(new HashSet<>(items))
                .build();
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/add/item")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Cart> addCartItemToCart(@RequestBody CartItemDto cartItemDto) {
        Cart cart = cartService.findCartById(Long.valueOf(cartItemDto.getCartId()));
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setQuantity(cartItemDto.getQuantity());
        item.setProductId(cartItemDto.getProductId());
        cartService.createCartItem(item);

        return ResponseEntity.ok(cart);
    }

    @PutMapping("/update/item/quantity")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Void> itemQuantityUpdate(@RequestBody CartItemDto cartItemDto) {
        CartItem cartItem = cartService.findAllCartItemsByCartId(Long.valueOf(cartItemDto.getCartId()))
                .stream()
                .filter(item -> item.getId().equals(Long.valueOf(cartItemDto.getId())))
                .findFirst()
                .orElse(null);

        if (cartItem == null) {
            return ResponseEntity.notFound().build();
        }

        cartItem.setQuantity(cartItemDto.getQuantity());
        cartService.updateCartItemQuantity(cartItem);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/item")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Void> deleteCartItem(@RequestBody CartQueryDto cartQueryDto) {
        if (cartQueryDto.getCartItemIdForDelete() == null) {
            return ResponseEntity.badRequest().build();
        }

        CartItem cartItem = cartService.findAllCartItemsByCartId(Long.valueOf(cartQueryDto.getCartId()))
                .stream()
                .filter(item -> item.getId().equals(Long.valueOf(cartQueryDto.getCartItemIdForDelete())))
                .findFirst()
                .orElse(null);

        if (cartItem == null) {
            return ResponseEntity.notFound().build();
        }

        cartService.deleteCartItem(cartItem);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/emptycart")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Void> emptyCart(@RequestBody CartQueryDto cartQueryDto) {
        if (cartQueryDto.getCartId() == null) {
            return ResponseEntity.badRequest().build();
        }

        cartService.deleteAllCartItemsByCartId(Long.valueOf(cartQueryDto.getCartId()));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletecart")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Void> deleteCart(@RequestBody CartQueryDto cartQueryDto) {
        if (cartQueryDto.getCartId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Cart cart = cartService.findCartById(Long.valueOf(cartQueryDto.getCartId()));
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        // First delete all cart items
        cartService.deleteAllCartItemsByCartId(cart.getId());

        // Then delete the cart
        cartService.deleteCart(cart);

        return ResponseEntity.ok().build();
    }
}