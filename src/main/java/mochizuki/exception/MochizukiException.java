package mochizuki.exception;

/**
 * Represents user-facing errors in the Mochizuki application.
 */
public class MochizukiException extends Exception {
    /**
     * Creates an exception with the given message.
     *
     * @param message error message
     */
    public MochizukiException(String message) {
        super(message);
    }
}
