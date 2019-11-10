package pl.sda.ratemymeme.exception;

public class MemeNotFoundException extends RuntimeException {
    public MemeNotFoundException(String message) {
        super(message);
    }
}
