package hu.terray.receipttovoucher.common.exception.handler;

import hu.terray.receipttovoucher.common.exception.system.badrequest.BadRequestException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception handler for {@link BadRequestException}.
 */
@Provider
public class AuthenticationFailedExceptionHandler implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
