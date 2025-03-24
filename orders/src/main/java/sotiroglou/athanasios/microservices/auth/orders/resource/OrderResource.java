package sotiroglou.athanasios.microservices.auth.orders.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import sotiroglou.athanasios.microservices.auth.model.carts.Cart;
import sotiroglou.athanasios.microservices.auth.model.carts.CartItem;
import sotiroglou.athanasios.microservices.auth.model.catalogue.dto.SockIdListDto;
import sotiroglou.athanasios.microservices.auth.model.catalogue.dto.SockPriceDto;
import sotiroglou.athanasios.microservices.auth.model.payment.AuthoriseRequest;
import sotiroglou.athanasios.microservices.auth.model.payment.AuthoriseResponse;
import sotiroglou.athanasios.microservices.auth.model.users.Address;
import sotiroglou.athanasios.microservices.auth.model.users.Card;
import sotiroglou.athanasios.microservices.auth.model.users.User;
import sotiroglou.athanasios.microservices.auth.orders.model.ApiResponse;
import sotiroglou.athanasios.microservices.auth.orders.model.Order;
import sotiroglou.athanasios.microservices.auth.orders.service.CartService;
import sotiroglou.athanasios.microservices.auth.orders.service.CatalogueService;
import sotiroglou.athanasios.microservices.auth.orders.service.PaymentsService;
import sotiroglou.athanasios.microservices.auth.orders.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Path("/orders")
public class OrderResource {

    @Inject
    @RestClient
    CartService cartService;

    @Inject
    @RestClient
    CatalogueService catalogueService;

    @Inject
    @RestClient
    UserService userService;

    @Inject
    @RestClient
    PaymentsService paymentsService;

    @GET
    @Path("/{customerId}")
    public ApiResponse createOrder(@PathParam("customerId") String customerId) throws ExecutionException, InterruptedException {
        // get cart with cart items
        Cart cart = cartService.getCartByCustomerId(customerId).thenApply(
                cart1 -> {
                    return cart1;
                }
        ).toCompletableFuture().get();
        Map<String, Integer> sockIdMap = cart.getCartItems()
                .stream().collect(Collectors.toMap(
                        item -> item.productId.toString(),
                        CartItem::getQuantity
                ));
        sockIdMap.forEach((k, v) -> {
            System.out.printf("%s = %d%n", k, v);
        });

        // use cart items ids to fetch each items price
        SockIdListDto sockIdListDto = SockIdListDto.builder()
                .sockIds(sockIdMap.keySet().stream().toList()).build();
        sockIdListDto.getSockIds().forEach(
                a -> System.out.println("sockId = " + a)
        );
        List<SockPriceDto> sockPricesDtoList = catalogueService.getSockPriceDto(sockIdListDto)
                .thenApply(dto -> dto).toCompletableFuture().get();

        sockPricesDtoList.forEach(
                a -> {
                    System.out.println("Sock price: " + a.getPrice());
                }
        );
        // calculate payment total and request payment auth
        float amount = (float) sockPricesDtoList.stream()
                .mapToDouble(item -> {
                    int quantity = sockIdMap.get(item.getSockId());
                    return item.getPrice() * quantity;
                }).sum();
        AuthoriseRequest authoriseRequest = new AuthoriseRequest(amount);
        System.out.println(authoriseRequest.getAmount());
        AuthoriseResponse authoriseResponse = paymentsService.getPaymentAuthorization(authoriseRequest)
                .thenApply(dto -> dto).toCompletableFuture().get();

        if (authoriseResponse.isAuthorised()) {
            // payment is cleared, request USER data to store Order in DB
            User user = userService.getUserById(customerId)
                    .thenApply(dto -> dto).toCompletableFuture().get();
            List<Address> addresses = userService.getAddresses(customerId)
                    .thenApply(dto -> dto).toCompletableFuture().get();
            List<Card> cards = userService.getCards(customerId)
                    .thenApply(dto -> dto).toCompletableFuture().get();

            Order order = new Order();
            order.setCustomerId(user.customerId);
            order.setOrderDate(LocalDate.now());
            order.setTotalAmount(amount);
            order.setShippingAddress(
                    addresses.get(0).toString()
            );
            order.setCardDetails(
                    cards.get(0).toString()
            );

            Order.persist(order);

            return new ApiResponse(200, "ORDER CREATED");

        }

        return new ApiResponse(400, "ORDER REQUEST FAILED");

    }
}
