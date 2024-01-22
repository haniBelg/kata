package kata.medical.kata.business.exceptions;

public class NotFoundEntity extends RuntimeException{
    public NotFoundEntity(String message){
        super(message);
    }
}
