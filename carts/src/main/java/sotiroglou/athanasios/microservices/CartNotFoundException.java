package sotiroglou.athanasios.microservices;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(){
        super();
    }

    public CartNotFoundException(String message){
        super(message);
    }
}
