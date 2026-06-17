package exceptions;

/**
 * Thrown when a withdrawal exceeds the available balance.
 */
public class InsufficientFundsException extends Exception {

    private String message;

    public InsufficientFundsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}