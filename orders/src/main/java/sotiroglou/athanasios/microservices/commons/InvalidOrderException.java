package sotiroglou.athanasios.microservices.commons;

public class InvalidOrderException extends IllegalStateException {
    public InvalidOrderException(String s) {
        super(s);
    }
}