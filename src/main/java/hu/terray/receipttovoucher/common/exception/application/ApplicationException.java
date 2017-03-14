package hu.terray.receipttovoucher.common.exception.application;


/**
 * Checked exceptions. Represents exceptional cases that are related to the business flow.
 *
 *
 */
public class ApplicationException extends Exception {

    /**
     * Constructor of {@link ApplicationException} object.
     *
     * @param message
     *            message of exception
     */
    public ApplicationException(final String message) {
        super(message);
    }

    /**
     * Constructor of {@link ApplicationException}.
     *
     * @param message
     *            message of exception
     * @param cause
     *            cause of exception
     */
    public ApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
