package sotiroglou.athanasios.microservices.tester.service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sotiroglou.athanasios.microservices.model.carts.Cart;
import sotiroglou.athanasios.microservices.model.carts.dto.CartItemDto;
import sotiroglou.athanasios.microservices.model.carts.dto.CartQueryDto;
import sotiroglou.athanasios.microservices.model.carts.dto.CartResponseDto;
import sotiroglou.athanasios.microservices.model.carts.dto.CreateCartDto;
import sotiroglou.athanasios.microservices.tester.utils.RequestUUIDHeaderFactory;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Path("/")
@RegisterRestClient
@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
public interface CartService {


    @GET
    @Path("")
    CompletionStage<List<CartResponseDto>> getAllCarts();

    @POST
    @Path("add/item")
    CompletionStage<Cart> addItem(
            @RequestBody CartItemDto cartItemDto
    );

    @POST
    @Path("create")
    CompletionStage<Cart> createCart(
            @RequestBody CreateCartDto createCartDto
    );

    @GET
    @Path("customer/{customerId}")
    CompletionStage<CartResponseDto> getCartByCustomerId(
            @PathParam("customerId") String customerId
    );

    @DELETE
    @Path("delete/item")
    CompletionStage<Response> deleteItem(
            @RequestBody CartQueryDto cartQueryDto
    );

    @DELETE
    @Path("emptycart")
    CompletionStage<Response> emptyCart(
            @RequestBody CartQueryDto cartQueryDto
    );


    @DELETE
    @Path("deletecart")
    CompletionStage<Response> deleteCart(
            @RequestBody CartQueryDto cartQueryDto
    );

    @PUT
    @Path("update/item/quantity")
    CompletionStage<Response> updateItemQuantity(
            @RequestBody CartItemDto cartItemDto
    );


}
