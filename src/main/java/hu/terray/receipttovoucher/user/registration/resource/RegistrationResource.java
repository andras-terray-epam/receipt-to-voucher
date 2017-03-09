package hu.terray.receipttovoucher.user.registration.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;
import hu.terray.receipttovoucher.user.registration.service.RegistrationService;

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
    public Response retrieveClubs(RegistrationRequest registrationRequest) {
        LOGGER.error("Getting reg request");
        if(registrationService.register(registrationRequest)){
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }


}
