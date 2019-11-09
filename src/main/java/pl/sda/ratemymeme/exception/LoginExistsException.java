package pl.sda.ratemymeme.exception;

public class LoginExistsException extends RuntimeException {
    public LoginExistsException(String message) {
        super(message);
    }
}
