package sotiroglou.athanasios.microservices.auth.tester.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import sotiroglou.athanasios.microservices.auth.model.carts.Cart;
import sotiroglou.athanasios.microservices.auth.model.carts.dto.CartItemDto;
import sotiroglou.athanasios.microservices.auth.model.carts.dto.CartQueryDto;
import sotiroglou.athanasios.microservices.auth.model.carts.dto.CartResponseDto;
import sotiroglou.athanasios.microservices.auth.model.carts.dto.CreateCartDto;
import sotiroglou.athanasios.microservices.auth.tester.service.CartService;

@Path("/tester/carts")
public class CartResource {

    @Inject
    @RestClient
    CartService cartService;

    @GET
    public Response testCarts () throws Exception {
        int initQuantity = 10;
        int updatedQuantity = 100;
        String customerId = "221111111111111111111111";
        boolean createCart = true;

        try {
            CartResponseDto cartResponseDto1 = cartService.getCartByCustomerId(customerId)
                    .thenApply(dto -> dto).toCompletableFuture().get();
            CartQueryDto cartQueryDto = CartQueryDto.builder().cartId(cartResponseDto1.getCartId()).build();
            Response emptyCart = cartService.deleteCart(cartQueryDto)
                    .thenApply(dto -> dto).toCompletableFuture().get();
        } catch (Exception e) {
            // nothing
        }

        CreateCartDto createCartDto = CreateCartDto.builder().customerId(customerId).build();
        Cart createdCart = cartService.createCart(createCartDto)
                .thenApply(dto -> dto).toCompletableFuture().get();

        // add item
        CartItemDto addItemDto = CartItemDto.builder()
                .cartId(createdCart.id.toString())
                .quantity(initQuantity)
                .productId("111111111111111111111111")
                .build();
        Cart updatedCart = cartService.addItem(addItemDto)
                .thenApply(dto -> dto).toCompletableFuture().get();

        //
        CartResponseDto cartResponseDto = cartService.getCartByCustomerId(customerId)
                .thenApply(dto -> dto).toCompletableFuture().get();
        if (cartResponseDto.getCartItems().stream().findFirst().get().quantity != initQuantity) {
            throw new Exception("Quantity is not correct");
        }

        String cartItemId = cartResponseDto.getCartItems().stream().findFirst().get().id.toString();
        addItemDto.setQuantity(updatedQuantity);
        addItemDto.setId(cartItemId);
        Response updateQuantityResponse = cartService.updateItemQuantity(addItemDto)
                .thenApply(dto -> dto).toCompletableFuture().get();
        cartResponseDto = cartService.getCartByCustomerId(customerId)
                .thenApply(dto -> dto).toCompletableFuture().get();
        if (cartResponseDto.getCartItems().stream().filter(
                a -> a.id.toString().equals(cartItemId)
        ).toList().get(0).quantity != updatedQuantity) {
            throw new Exception("Quantity is not updated correctly");
        }

        // add second item
        addItemDto.setProductId("222222222222222222222222");
        updatedCart = cartService.addItem(addItemDto)
                .thenApply(dto -> dto).toCompletableFuture().get();
        cartResponseDto = cartService.getCartByCustomerId(customerId)
                .thenApply(dto -> dto).toCompletableFuture().get();
        if (cartResponseDto.getCartItems().size() != 2) {
            throw new Exception("2nd Item not added");
        }


        CartQueryDto cartQueryDto = CartQueryDto.builder()
                .cartItemIdForDelete(cartResponseDto.getCartItems().stream().findFirst().get().id.toString())
                .build();
        Response deleteItem = cartService.deleteItem(cartQueryDto)
                .thenApply(dto -> dto).toCompletableFuture().get();
        cartResponseDto = cartService.getCartByCustomerId(customerId)
                .thenApply(dto -> dto).toCompletableFuture().get();
        if (cartResponseDto.getCartItems().size() != 1) {
            throw new Exception("Item not deleted correctly");
        }

        cartQueryDto.setCartId(cartResponseDto.getCartId());
        Response emptyCart = cartService.emptyCart(cartQueryDto)
                .thenApply(dto -> dto).toCompletableFuture().get();
        cartResponseDto = cartService.getCartByCustomerId(customerId)
                .thenApply(dto -> dto).toCompletableFuture().get();
        if (!cartResponseDto.getCartItems().isEmpty()) {
            throw new Exception("Cart not emptied. Error");
        }

        return Response.ok().build();

    }
}
