package hu.terray.receipttovoucher.common.exception;

import java.io.Serializable;

/**
 * Custom exception to throw when user creation is not successful.
 */
public class RTVUserCreationFailedException extends RuntimeException implements Serializable {

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
