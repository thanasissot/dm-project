package sotiroglou.athanasios.microservices.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import sotiroglou.athanasios.microservices.entity.Cart;
import sotiroglou.athanasios.microservices.entity.CartItem;
import sotiroglou.athanasios.microservices.repository.CartItemRepository;
import sotiroglou.athanasios.microservices.repository.CartRepository;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    @Inject
    CartRepository cartRepository;

    @Inject
    CartItemRepository cartItemRepository;

    @GET
    @Path("/")
    public List<Cart> getAllCarts() {
        return cartRepository.findAllCarts();
    }

    @GET
    @Path("/get/all/{customerId}")
    public List<Cart> getAllCartsByCustomerId(
            @PathParam("customerId") String customerId
    ) {
        return cartRepository.findAllCartsByCustomerId(new ObjectId(customerId));
    }

    @POST
    public Response createCart(
            @RequestBody CartDto cart
    ) {
        Cart cart1 = Cart.builder()
                .customerId(cart.getCustomerId())
                .session(cart.getSession())
                .build();
        cartRepository.persist(cart1);

        if (!cart.getCartItems().isEmpty()) {
            List<Cart> carts = cartRepository.findAllCartsByCustomerIdAndSession(
                    cart.getCustomerId(), cart.getSession());
            if (!carts.isEmpty()) {
                Cart cartNew = carts.getFirst();
                cart.getCartItems().forEach(
                        cartItem -> {
                            CartItem.persist(
                                    CartItem.builder()
                                            .cartId(cartNew.id)
                                            .productId(cartItem.getProductId())
                                            .quantity(1)
                                            .unitPrice(cartItem.getUnitPrice())
                                            .build()
                            );
                        }
                );
            }


            return Response.status(Response.Status.CREATED).entity(cart1).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/get/{customerId}/{session}")
    public Response getCart(@PathParam("customerId") String customerId,
                            @PathParam("session") String session
    ) {
        Cart cart = getCart(new ObjectId(customerId), session);
        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<CartItem> cartItems = cartItemRepository.findAllCartItemsByCartId(cart.id);
        System.out.println(Arrays.toString(cartItems.toArray()));
        CartDto cartDto =
                CartDto.builder()
                        .cartItems(
                                cartItems.stream().map(
                                        cartItem -> CartItemDto.builder()
                                                .productId(cartItem.getProductId())
                                                .unitPrice(cartItem.getUnitPrice())
                                                .quantity(cartItem.getQuantity())
                                                .cartId(cartItem.getCartId())
                                                .build()
                                ).toList()
                        )
                        .session(cart.getSession())
                        .customerId(cart.getCustomerId())
                        .build();
        return Response.status(Response.Status.OK).entity(cartDto).build();
    }

    @DELETE
    @Path("/delete/{customerId}/{session}")
    public Response deleteCart(@PathParam("customerId") String customerId,
                               @PathParam("session") String session) {

        Cart cart = getCart(new ObjectId(customerId), session);
        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        long result = Cart.delete("_id", cart.id);

        List<CartItem> cartItems = cartItemRepository.findAllCartItemsByCartId(cart.id);
        CartItem.delete("cartId", cart.id);

        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("/items/{customerId}/{session}")
    public Response getCartItems(@PathParam("customerId") String customerId,
                                 @PathParam("session") String session) {
        Cart cart = getCart(new ObjectId(customerId), session);
        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<CartItem> cartItems = cartItemRepository.findAllCartItemsByCartId(cart.id);
        return Response.status(Response.Status.OK).entity(cartItems).build();
    }

    @POST
    @Path("/items/add/{customerId}/{session}")
    public Response addItemToCart(@PathParam("customerId") String customerId,
                                  @PathParam("session") String session,
                                  @RequestBody CartItemDto cartItemDto) {
        Cart cart = getCart(new ObjectId(customerId), session);
        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CartItem cartItem =
                CartItem.builder()
                        .cartId(cartItemDto.getCartId())
                        .productId(cartItemDto.getProductId())
                        .unitPrice(cartItemDto.getUnitPrice())
                        .quantity(1)
                        .build();

        CartItem.persist(cartItem);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/items/delete/{cartItemId}")
    public Response deleteCartItem(@PathParam("cartItemId") String cartItemId){
        CartItem cartItem = CartItem.findById(new ObjectId(cartItemId));
        if (cartItem == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        cartItemRepository.delete(cartItem);

        return Response.status(Response.Status.OK).build();
    }

    private Cart getCart (ObjectId customerId, String session) {
        List<Cart> carts = cartRepository.findAllCartsByCustomerIdAndSession(customerId, session);
        return carts.isEmpty() ? null : carts.getFirst();
    }
}
