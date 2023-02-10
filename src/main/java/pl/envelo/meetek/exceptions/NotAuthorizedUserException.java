package pl.envelo.meetek.exceptions;

public class NotAuthorizedUserException extends RuntimeException {

    public NotAuthorizedUserException(String message) {
        super(message);
    }

}
