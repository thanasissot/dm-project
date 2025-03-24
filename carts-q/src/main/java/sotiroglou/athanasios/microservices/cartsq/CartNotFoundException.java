package sotiroglou.athanasios.microservices.cartsq;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(){
        super();
    }

    public CartNotFoundException(String message){
        super(message);
    }
}
