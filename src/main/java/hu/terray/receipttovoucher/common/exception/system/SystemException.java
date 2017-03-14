package hu.terray.receipttovoucher.common.exception.system;

import java.io.Serializable;

/**
 * Unchecked exceptions. Mainly represents exceptional cases not related to the business flow.
 *
 *
 */
public class SystemException extends RuntimeException implements Serializable {

    /**
     * Constructor of {@link SystemException}.
     *
     * @param message
     *            message of exception
     */
    public SystemException(final String message) {
        super(message);
    }

    /**
     * Constructor of {@link SystemException}.
     *
     * @param cause
     *            cause of exception
     */
    public SystemException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructor of {@link SystemException}.
     *
     * @param message
     *            message of exception
     * @param cause
     *            cause of exception
     */
    public SystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
