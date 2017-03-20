package hu.terray.receipttovoucher.common.response;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

import hu.terray.receipttovoucher.common.exception.system.internalserver.InternalServerException;

/**
 * Factory to create responses.
 */
@Singleton
public class ResponseFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseFactory.class);

    /**
     * Response creation with created - 201 status code.
     *
     * @param uri Uri to set in response.
     * @param entity Object to send back as json.
     * @return Response with given URI in header and entity as body.
     */
    public Response responseWithCreatedStatus(String uri, Object entity) {
        Response response = null;
        try {
            response = Response.created(new URI(uri)).entity(entity).build();
        } catch (URISyntaxException e) {
            LOGGER.error("The URI given to response generator is wrong. URI:" + uri, e);
            throw new InternalServerException("Internal server error during response generation", e);
        }
        return response;
    }
}
