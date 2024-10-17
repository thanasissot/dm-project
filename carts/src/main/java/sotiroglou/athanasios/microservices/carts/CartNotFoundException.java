package sotiroglou.athanasios.microservices.carts;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(){
        super();
    }

    public CartNotFoundException(String message){
        super(message);
    }
}
