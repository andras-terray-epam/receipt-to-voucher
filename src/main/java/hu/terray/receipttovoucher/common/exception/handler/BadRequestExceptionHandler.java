package hu.terray.receipttovoucher.common.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import hu.terray.receipttovoucher.common.exception.system.badrequest.BadRequestException;

/**
 * Exception handler for {@link BadRequestException}.
 */
@Provider
public class BadRequestExceptionHandler implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
