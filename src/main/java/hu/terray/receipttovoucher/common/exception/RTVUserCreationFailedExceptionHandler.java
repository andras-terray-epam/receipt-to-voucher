package hu.terray.receipttovoucher.common.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception handler for {@link RTVUserCreationFailedException}.
 */
@Provider
public class RTVUserCreationFailedExceptionHandler implements ExceptionMapper<RTVUserCreationFailedException> {

    @Override
    public Response toResponse(RTVUserCreationFailedException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
