package hu.terray.receipttovoucher.common.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import hu.terray.receipttovoucher.common.exception.system.internalserver.InternalServerException;

/**
 * Exception handler for {@link InternalServerException}.
 */
@Provider
public class InternalServerExceptionHandler implements ExceptionMapper<InternalServerException> {

    @Override
    public Response toResponse(InternalServerException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
    }

}
