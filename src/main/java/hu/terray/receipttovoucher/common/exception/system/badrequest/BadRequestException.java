package hu.terray.receipttovoucher.common.exception.system.badrequest;

import hu.terray.receipttovoucher.common.exception.system.SystemException;

/**
 * Bad request exceptions.
 */
public class BadRequestException extends SystemException {

    /**
     * Message and cause are mandatory fields.
     *
     * @param message Exception message.
     * @param cause   Cause exception.
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
