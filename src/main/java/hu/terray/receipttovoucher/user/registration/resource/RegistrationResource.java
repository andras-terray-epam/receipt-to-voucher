package hu.terray.receipttovoucher.user.registration.resource;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.common.response.ResponseFactory;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationResponse;
import hu.terray.receipttovoucher.user.registration.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Accepts registration requests.
 */
@Path("/api/v1/registration")
@Produces(APPLICATION_JSON)
public class RegistrationResource {

    static final String GET_ACTUAL_USER_ENDPOINT = "/api/v1/users/me";
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationResource.class);

    private RegistrationService registrationService;

    private ResponseFactory responseFactory;

    /**
     * Constructor with necessary dependencies.
     *
     * @param registrationService dependency.
     * @param responseFactory     dependency.
     */
    @Inject
    public RegistrationResource(final RegistrationService registrationService, final ResponseFactory responseFactory) {
        this.registrationService = registrationService;
        this.responseFactory = responseFactory;
    }

    /**
     * User registration POST endpoint.
     *
     * @param registrationRequest user registration request details.
     * @return id of the created user.
     * @throws URISyntaxException syntax exception.
     */
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response register(RegistrationRequest registrationRequest) throws URISyntaxException {
        LOGGER.info("Getting reg request");
        RegistrationResponse registrationResponse = registrationService.register(registrationRequest);
        return responseFactory.responseWithCreatedStatus(GET_ACTUAL_USER_ENDPOINT, registrationResponse);
    }

}
