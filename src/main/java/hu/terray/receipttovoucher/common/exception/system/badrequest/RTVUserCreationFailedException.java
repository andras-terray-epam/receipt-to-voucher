package hu.terray.receipttovoucher.common.exception.system.badrequest;

/**
 * Custom exception to throw when user creation is not successful.
 */
public class RTVUserCreationFailedException extends BadRequestException {

    /**
     * Message and cause exception are mandatory fields.
     *
     * @param message Exception message.
     * @param cause   Cause exception.
     */
    public RTVUserCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
