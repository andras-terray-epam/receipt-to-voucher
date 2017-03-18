package hu.terray.receipttovoucher.user.login.resource;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.user.login.resource.domain.LoginRequest;
import hu.terray.receipttovoucher.user.login.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Accepts registration requests.
 */
@Path("/api/v1/login")
@Produces(APPLICATION_JSON)
public class LoginResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);

    private final AuthenticationService authenticationService;

    /**
     * Constructor with necessary dependencies.
     *
     * @param authenticationService dependency.
     */
    @Inject
    public LoginResource(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * User login POST endpoint.
     *
     * @param loginRequest user registration request details.
     * @return token to authenticate in the future.
     */
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response register(LoginRequest loginRequest){
        LOGGER.info("Getting login request");
        String token = authenticationService.areLoginCredentialsValid(loginRequest.getEmail(), loginRequest.getPassword());
        return Response.ok().entity(token).build();
    }

}
