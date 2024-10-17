package sotiroglou.athanasios.microservices.carts.resource;

import com.mongodb.client.model.Filters;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import sotiroglou.athanasios.microservices.carts.CartNotFoundException;
import sotiroglou.athanasios.microservices.carts.carts.Cart;
import sotiroglou.athanasios.microservices.carts.carts.CartItem;
import sotiroglou.athanasios.microservices.carts.dto.CartItemDto;
import sotiroglou.athanasios.microservices.carts.dto.CartQueryDto;
import sotiroglou.athanasios.microservices.carts.dto.CartResponseDto;
import sotiroglou.athanasios.microservices.carts.dto.CreateCartDto;

import java.util.HashSet;
import java.util.List;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {


    @GET
    @Path("/")
    public Iterable<CartResponseDto> getAllCarts() {
        List<Cart> carts = Cart.findAll().list();
        return carts.stream().map(
                cart -> {
                    List<CartItem> items =  CartItem.list("cartId", cart.id);
                    return CartResponseDto.builder().customerId(cart.customerId.toString())
                            .cartId(cart.id.toString())
                            .cartItems(new HashSet<>(items)).build();
                }
        ).toList();
    }

    // Create a new cart
    @POST
    @Path("/create")
    public Response createCart(CreateCartDto createCartDto) {
        Cart cart = new Cart();
        cart.setCustomerId(new ObjectId(createCartDto.getCustomerId()));
        Cart.persist(cart);
        return Response.status(Response.Status.CREATED).entity(cart).build();
    }


    // Get a cart by ID
    @GET
    @Path("/customer/{id}")
    public Response getCartByCustomer(@PathParam("id") String id) {
        Cart cart = Cart.find("customerId", new ObjectId(id)).firstResult();
        // Fetch the CartItems using the IDs from the Cart
        List<CartItem> items = CartItem.list("cartId", cart.id);
        CartResponseDto responseDto = CartResponseDto.builder().customerId(cart.customerId.toString())
                .cartId(cart.id.toString())
                .cartItems(new HashSet<>(items)).build();
        return Response.ok(responseDto).build();
    }


    @POST
    @Path("/add/item")
    public Response addCartItemToCart(
            @RequestBody(required = true) final CartItemDto cartItemDto
    ) {
        Cart cart = Cart.findById(new ObjectId(cartItemDto.getCartId()));

        CartItem c = new CartItem();
        c.setCartId(cart.id);
        System.out.println(c.cartId);
        c.setQuantity(cartItemDto.getQuantity());
        c.setProductId(new ObjectId(cartItemDto.getProductId()));
        CartItem.persist(c);
        return Response.status(Response.Status.OK).entity(cart).build();
    }

    @PUT
    @Path("/update/item/quantity")
    public Response itemQuantityUpdate(
            @RequestBody(required = true) final CartItemDto cartItemDto
    ) {
        CartItem cartItem = CartItem.findById(new ObjectId(cartItemDto.getId()));
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.persistOrUpdate();
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/delete/item")
    public Response deleteCartItem(
            @RequestBody(required = true) final CartQueryDto cartQueryDto
    ){
        if (cartQueryDto.getCartItemIdForDelete() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        CartItem cartItem = CartItem.findById(new ObjectId(cartQueryDto.getCartItemIdForDelete()));
        CartItem.deleteById(cartItem.id);

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/emptycart")
    public Response emptyCart(
            @RequestBody(required = true) final CartQueryDto cartQueryDto
    ) {
        assert cartQueryDto.getCartId() != null;
        List<CartItem> items = CartItem.list("cartId", new ObjectId(cartQueryDto.getCartId()));
        items.forEach(
                item -> CartItem.deleteById(item.id)
        );

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/deletecart")
    public Response deleteCart(
            @RequestBody(required = true) final CartQueryDto cartQueryDto
    ) {
        assert cartQueryDto.getCartId() != null;
        List<CartItem> items = CartItem.list("cartId", new ObjectId(cartQueryDto.getCartId()));
        items.forEach(
                item -> CartItem.deleteById(item.id)
        );

        Cart cart = Cart.findById(new ObjectId(cartQueryDto.getCartId()));
        Cart.deleteById(cart.id);

        return Response.status(Response.Status.OK).build();
    }


    private Cart getCart(CartQueryDto cartQueryDto) {
        if (cartQueryDto.getCartId() == null && cartQueryDto.getCustomerId() == null) {
            throw new IllegalArgumentException("CartId and CustomerId cannot be null at the same time");
        }

        if (cartQueryDto.getCartId() != null) {
            Cart cart = Cart.findById(cartQueryDto.getCartId());
            return cart;
        } else {
            Bson filter = Filters.eq("customerId", new ObjectId(String.valueOf(cartQueryDto.getCustomerId())));
            Cart cart = Cart.find("customerId", new ObjectId(String.valueOf(cartQueryDto.getCustomerId()))).firstResult();
            return cart;
        }

    }

    private Response getResponse(CartQueryDto cartQueryDto) {
        Cart cart = null;
        try {
            cart = getCart(cartQueryDto);
        } catch (CartNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok(cart).build();
    }


}

//
//    @Inject
//    CartItemRepository cartItemRepository;

//    @GET
//    @Path("/")
//    public List<Cart> getAllCarts() {
//        return cartRepository.findAllCarts();
//    }
//
//    @GET
//    @Path("/get/all/{customerId}")
//    public List<Cart> getAllCartsByCustomerId(
//            @PathParam("customerId") String customerId
//    ) {
//        return cartRepository.findAllCartsByCustomerId(new ObjectId(customerId));
//    }

//    @GET()
//    @Path("/{cartId}")
//    public Response getCartById(@PathParam("cartId") ObjectId cartId) {
////        ObjectId objectId = new ObjectId(cartId);
//        Optional<Cart> cart = Cart.findByIdOptional(cartId);
//        if (cart.isPresent()) {
//            return Response.ok(cart.get()).build();
//        }
//
//        return Response.status(Response.Status.NOT_FOUND).build();
//    }
//
//    @POST
//    public Response createCart(
//            @RequestBody CartDto cart
//    ) {
//        Cart cart1 = Cart.builder()
//                .customerId(cart.getCustomerId())
//                .build();
//
//        Cart.persist(cart1);
//
//        return Response.status(Response.Status.OK).entity(cart1).build();

//        if (!cart.getCartItems().isEmpty()) {
//            List<Cart> carts = cartRepository.findAllCartsByCustomerIdAndSession(
//                    cart.getCustomerId(), cart.getSession());
//            if (!carts.isEmpty()) {
//                Cart cartNew = carts.getFirst();
//                cart.getCartItems().forEach(
//                        cartItem -> {
//                            CartItem.persist(
//                                    CartItem.builder()
//                                            .cartId(cartNew.id)
//                                            .productId(cartItem.getProductId())
//                                            .quantity(1)
//                                            .unitPrice(cartItem.getUnitPrice())
//                                            .build()
//                            );
//                        }
//                );
//            }


//            return Response.status(Response.Status.CREATED).entity(cart1).build();
//        }

//    }

//    @GET
//    @Path("/get/{customerId}/{session}")
//    public Response getCart(@PathParam("customerId") String customerId,
//                            @PathParam("session") String session
//    ) {
//        Cart cart = getCart(new ObjectId(customerId), session);
//        if (cart == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//        List<CartItem> cartItems = cartItemRepository.findAllCartItemsByCartId(cart.id);
//        System.out.println(Arrays.toString(cartItems.toArray()));
//        CartDto cartDto =
//                CartDto.builder()
//                        .cartItems(
//                                cartItems.stream().map(
//                                        cartItem -> CartItemDto.builder()
//                                                .productId(cartItem.getProductId())
//                                                .unitPrice(cartItem.getUnitPrice())
//                                                .quantity(cartItem.getQuantity())
//                                                .cartId(cartItem.getCartId())
//                                                .build()
//                                ).toList()
//                        )
//                        .customerId(cart.getCustomerId())
//                        .build();
//        return Response.status(Response.Status.OK).entity(cartDto).build();
//    }
//
//    @DELETE
//    @Path("/delete/{customerId}/{session}")
//    public Response deleteCart(@PathParam("customerId") String customerId,
//                               @PathParam("session") String session) {
//
//        Cart cart = getCart(new ObjectId(customerId), session);
//        if (cart == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//        long result = Cart.delete("_id", cart.id);
//
//        List<CartItem> cartItems = cartItemRepository.findAllCartItemsByCartId(cart.id);
//        CartItem.delete("cartId", cart.id);
//
//        return Response.status(Response.Status.OK).entity(result).build();
//    }
//
//    @GET
//    @Path("/items/{customerId}/{session}")
//    public Response getCartItems(@PathParam("customerId") String customerId,
//                                 @PathParam("session") String session) {
//        Cart cart = getCart(new ObjectId(customerId), session);
//        if (cart == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//        List<CartItem> cartItems = cartItemRepository.findAllCartItemsByCartId(cart.id);
//        return Response.status(Response.Status.OK).entity(cartItems).build();
//    }
//
//    @POST
//    @Path("/items/add/{customerId}/{session}")
//    public Response addItemToCart(@PathParam("customerId") String customerId,
//                                  @PathParam("session") String session,
//                                  @RequestBody CartItemDto cartItemDto) {
//        Cart cart = getCart(new ObjectId(customerId), session);
//        if (cart == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//        CartItem cartItem =
//                CartItem.builder()
//                        .cartId(cartItemDto.getCartId())
//                        .productId(cartItemDto.getProductId())
//                        .unitPrice(cartItemDto.getUnitPrice())
//                        .quantity(1)
//                        .build();
//
//        CartItem.persist(cartItem);
//        return Response.status(Response.Status.OK).build();
//    }
//
//    @DELETE
//    @Path("/items/delete/{cartItemId}")
//    public Response deleteCartItem(@PathParam("cartItemId") String cartItemId){
//        CartItem cartItem = CartItem.findById(new ObjectId(cartItemId));
//        if (cartItem == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
//        cartItemRepository.delete(cartItem);
//
//        return Response.status(Response.Status.OK).build();
//    }

//    private Cart getCart (ObjectId customerId, String session) {
////        List<Cart> carts = cartRepository.findAllCartsByCustomerIdAndSession(customerId, session);
//        return carts.isEmpty() ? null : carts.getFirst();
//    }

//    private Cart getCart(ObjectId cartId) {
//        Cart cart = Cart.findByIdOptional(cartId).ifPresentOrElse();
//    }
//}
