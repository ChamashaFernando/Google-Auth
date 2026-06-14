package lk.chamasha.googleauth.exception;

public class InvalidGoogleTokenException
        extends RuntimeException {

    public InvalidGoogleTokenException(String message) {
        super(message);
    }
}