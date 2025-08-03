package exception;

/**
 * Custom Exception for invalid commands
 */
public class InvalidCommandException extends Exception {
    /**
     * Construct new custom exception with message.
     * @param message details of error
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
