package hu.terray.receipttovoucher.common.exception;

import java.io.Serializable;

/**
 * Custom exception to throw when user creation is not successful.
 */
public class RTVUserCreationFailedException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public RTVUserCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
