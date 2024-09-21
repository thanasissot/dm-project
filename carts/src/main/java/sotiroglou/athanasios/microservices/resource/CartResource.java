package sotiroglou.athanasios.microservices.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import sotiroglou.athanasios.microservices.CartNotFoundException;
import sotiroglou.athanasios.microservices.dto.CartDto;
import sotiroglou.athanasios.microservices.dto.CartQueryDto;
import sotiroglou.athanasios.microservices.entity.Cart;
import sotiroglou.athanasios.microservices.entity.CartItem;
import sotiroglou.athanasios.microservices.mapper.CartItemMapper;
import sotiroglou.athanasios.microservices.mapper.CartMapper;
import sotiroglou.athanasios.microservices.repository.CartItemRepository;
import sotiroglou.athanasios.microservices.repository.CartRepository;

import java.util.Optional;
import java.util.Set;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    @Inject
    CartRepository cartRepository;

    @Inject
    CartItemRepository cartItemRepository;


    @Inject
    CartMapper cartMapper;

    @Inject
    CartItemMapper cartItemMapper;

    @GET
    @Path("/")
    public Iterable<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @POST
    @Path("/get")
    public Response getCartById(
            @RequestBody(required = true) final CartQueryDto cartQueryDto
    ) {
        return getResponse(cartQueryDto);
    }

    @POST
    @Path("/get/by/customer")
    public Response getCartByCustomerId(
            @RequestBody(required = true) final CartQueryDto cartQueryDto
    ) {
        return getResponse(cartQueryDto);
    }


    @POST
    @Path("/add")
    public Response addCart(
            @RequestBody CartDto cartDto
    ) {
        Cart newCart = cartMapper.dtoToEntity(cartDto);
        for (CartItem cartItem : newCart.getCartItems()) {
            cartItem.setCart(newCart);
        }
        cartRepository.save(newCart);
        return Response.status(Response.Status.OK).entity(newCart).build();
    }

    @DELETE
    @Path("/delete")
    public Response deleteCart(
            @RequestBody(required = true) final CartQueryDto cartQueryDto
    ) {
        Response response = getResponse(cartQueryDto);
        if (response.getStatus() == 200) {
            Cart cart = (Cart) response.getEntity();
            cartRepository.delete(cart);
        }

        return response;
    }

    @POST
    @Path("/add/item")
    public Response addCartItems(
            @RequestBody(required = true) final CartQueryDto cartQueryDto
    ) {
        Response response = getResponse(cartQueryDto);
        if (response.getStatus() == 200) {
            Cart cart = (Cart) response.getEntity();
            Set<CartItem> cartItems = cartItemMapper.dtosToEntities(cartQueryDto.getCartItems());
            for (CartItem cartItem : cartItems) {
                cartItem.setCart(cart);
            }
            cartItemRepository.saveAll(cartItems);
            cart = cartRepository.findById(cart.getId()).get();
            return Response.status(Response.Status.OK).entity(cart).build();
        }

        return response;
    }

    @POST
    @Path("/update/item/quantity")
    public Response itemQuantityUpdate(
            @RequestBody(required = true) final CartQueryDto cartQueryDto
    ) {
        if (cartQueryDto.getItemQuantityUpdate() != null
                && cartQueryDto.getItemQuantityUpdate().getId() != null
        ) {
            Long itemId = cartQueryDto.getItemQuantityUpdate().getId();
            Optional<CartItem> optionalCartItem = cartItemRepository.findById(itemId);
            if (optionalCartItem.isPresent()) {
                CartItem cartItem = optionalCartItem.get();
                cartItem.setQuantity(cartQueryDto.getItemQuantityUpdate().getQuantity());
                cartItem = cartItemRepository.save(cartItem);
                return Response.status(Response.Status.OK).entity(cartItem).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("/delete/item")
    public Response deleteCartItem(
            @RequestBody(required = true) final CartQueryDto cartQueryDto
    ){
        if (cartQueryDto.getCartItemIdForDelete() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartQueryDto.getCartItemIdForDelete());
        if (optionalCartItem.isPresent()) {
            cartItemRepository.delete(optionalCartItem.get());
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    private Cart getCart(CartQueryDto cartQueryDto) {
        if (cartQueryDto.getCartId() == null && cartQueryDto.getCustomerId() == null) {
            throw new IllegalArgumentException("CartId and CustomerId cannot be null at the same time");
        }

        if (cartQueryDto.getCartId() != null) {
            Optional<Cart> cart = cartRepository.findById(cartQueryDto.getCartId());
            if (cart.isPresent()) {
                return cart.get();
            }
        } else {
            Optional<Cart> cart = cartRepository.findByCustomerId(cartQueryDto.getCustomerId());
            if (cart.isPresent()) {
                return cart.get();
            }
        }

        throw new CartNotFoundException(String.format("CartId=%s and CustomerId=%s do not exist in the DB", cartQueryDto.getCartId(), cartQueryDto.getCustomerId()));

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
