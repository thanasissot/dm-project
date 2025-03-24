package sotiroglou.athanasios.microservices.auth.orders.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidOrderExceptionMapper implements ExceptionMapper<InvalidOrderException> {

    @Override
    public Response toResponse(InvalidOrderException exception) {
        // Return a 406 Not Acceptable status code with the exception message
        return Response.status(Response.Status.NOT_ACCEPTABLE)
                .entity(exception.getMessage())
                .build();
    }
}
