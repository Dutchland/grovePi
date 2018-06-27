package nl.grovepi.test.units;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Exception cause) {
        super(message, cause);
    }
}
