package hu.terray.receipttovoucher.common.exception.system.internalserver;

import hu.terray.receipttovoucher.common.exception.system.SystemException;

/**
 * Created by Andras_Terray on 3/14/2017.
 */
public class InternalServerException extends SystemException {

    /**
     * Message and cause exception are mandatory fields.
     *
     * @param message Exception message.
     * @param cause   Cause exception.
     */
    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
