package oncall.exception.custom;

public class InvalidException extends IllegalArgumentException {
    private static final String INVALID_MESSAGE_PREFIX = "[ERROR] ";

    public InvalidException(String message) {
        super(String.join(INVALID_MESSAGE_PREFIX, message));
    }
}
