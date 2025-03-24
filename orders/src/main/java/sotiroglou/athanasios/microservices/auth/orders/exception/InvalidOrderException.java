package sotiroglou.athanasios.microservices.orders.exception;

public class InvalidOrderException extends IllegalStateException {
    public InvalidOrderException(String s) {
        super(s);
    }
}