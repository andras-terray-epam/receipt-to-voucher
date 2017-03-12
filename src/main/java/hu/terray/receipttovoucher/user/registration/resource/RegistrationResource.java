package hu.terray.receipttovoucher.user.registration.resource;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationResponse;
import hu.terray.receipttovoucher.user.registration.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Accepts registration requests.
 */
@Path("/api/v1/registration")
@Produces(MediaType.APPLICATION_JSON)
public class RegistrationResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationResource.class);

    private RegistrationService registrationService;

    @Inject
    public RegistrationResource(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response retrieveClubs(RegistrationRequest registrationRequest) throws URISyntaxException {
        LOGGER.info("Getting reg request");
        RegistrationResponse registrationResponse = registrationService.register(registrationRequest);
        return Response.created(new URI("/api/v1/users/me")).entity(registrationResponse).build();
    }


}
