package sotiroglou.athanasios.microservices.cartsq.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import sotiroglou.athanasios.microservices.cartsq.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    @Inject
    CartRepository cartRepository;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    public List<CartResponseDto> getAllCarts() {
        // Make a defensive copy of the carts list
        List<Cart> carts = new ArrayList<>(cartRepository.findAllCarts());

        return carts.stream()
                .map(cart -> {
                    List<CartItem> items = cartRepository.findAllCartItemsByCartId(cart.getId());
                    return CartResponseDto.builder()
                            .customerId(cart.getCustomerId())
                            .cartId(String.valueOf(cart.getId()))
                            .cartItems(new HashSet<>(items))
                            .build();
                })
                .toList();
    }

    @POST
    @Path("/create")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    public Response createCart(CreateCartDto createCartDto) {
        Cart cart = new Cart();
        cart.setCustomerId(createCartDto.getCustomerId());
        cartRepository.createCart(cart);
        return Response.status(Response.Status.CREATED).entity(cart).build();
    }

    @GET
    @Path("/customer/{id}")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    public Response getCartByCustomer(@PathParam("id") String id) {
        Cart cart = cartRepository.findCartByCustomerId(id);
        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<CartItem> items = cartRepository.findAllCartItemsByCartId(cart.getId());
        CartResponseDto responseDto = CartResponseDto.builder()
                .customerId(cart.getCustomerId())
                .cartId(String.valueOf(cart.getId()))
                .cartItems(new HashSet<>(items))
                .build();
        return Response.ok(responseDto).build();
    }

    @POST
    @Path("/add/item")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    public Response addCartItemToCart(CartItemDto cartItemDto) {
        Cart cart = cartRepository.findCartById(Long.valueOf(cartItemDto.getCartId()));
        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setQuantity(cartItemDto.getQuantity());
        item.setProductId(cartItemDto.getProductId());
        cartRepository.createCartItem(item);

        return Response.status(Response.Status.OK).entity(cart).build();
    }

    @PUT
    @Path("/update/item/quantity")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    public Response itemQuantityUpdate(CartItemDto cartItemDto) {
        CartItem cartItem = CartItem.findById(Long.valueOf(cartItemDto.getId()));
        if (cartItem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.persist();

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/delete/item")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    public Response deleteCartItem(CartQueryDto cartQueryDto) {
        if (cartQueryDto.getCartItemIdForDelete() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        CartItem cartItem = CartItem.findById(Long.valueOf(cartQueryDto.getCartItemIdForDelete()));
        if (cartItem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        cartRepository.deleteCartItem(cartItem);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/emptycart")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    public Response emptyCart(CartQueryDto cartQueryDto) {
        if (cartQueryDto.getCartId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        cartRepository.deleteAllCartItemsByCartId(Long.valueOf(cartQueryDto.getCartId()));
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/deletecart")
    @RolesAllowed({"USER", "ADMIN", "SUPERADMIN"})
    public Response deleteCart(CartQueryDto cartQueryDto) {
        if (cartQueryDto.getCartId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Cart cart = cartRepository.findCartById(Long.valueOf(cartQueryDto.getCartId()));
        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // First delete all cart items
        cartRepository.deleteAllCartItemsByCartId(cart.getId());

        // Then delete the cart
        cartRepository.deleteCart(cart);

        return Response.status(Response.Status.OK).build();
    }
}