package exceptions;

/**
 * Thrown when an invalid amount is deposited or withdrawn.
 */
public class InvalidAmountException extends Exception {

    private String message;

    public InvalidAmountException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}