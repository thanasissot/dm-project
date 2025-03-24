package sotiroglou.athanasios.microservices.auth.orders.exception;

public class InvalidOrderException extends IllegalStateException {
    public InvalidOrderException(String s) {
        super(s);
    }
}