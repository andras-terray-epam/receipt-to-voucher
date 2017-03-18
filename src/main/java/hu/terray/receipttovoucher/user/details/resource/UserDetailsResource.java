package hu.terray.receipttovoucher.user.details.resource;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.common.exception.system.badrequest.AuthenticationFailedException;
import hu.terray.receipttovoucher.user.details.resource.domain.UserDetails;
import hu.terray.receipttovoucher.user.details.resource.transformer.UserDetailsTransformer;
import hu.terray.receipttovoucher.user.details.service.UserDetailsService;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import io.dropwizard.auth.Auth;
import io.dropwizard.auth.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.security.Principal;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Accepts registration requests.
 */
@Path("/api/v1/users")
@Produces(APPLICATION_JSON)
public class UserDetailsResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsResource.class);

    private final UserDetailsService userDetailsService;
    private final UserDetailsTransformer userDetailsTransformer;

    /**
     * Constructor with necessary dependencies.
     *
     * @param userDetailsService     dependency.
     * @param userDetailsTransformer dependency.
     */
    @Inject
    public UserDetailsResource(final UserDetailsService userDetailsService, final UserDetailsTransformer userDetailsTransformer) {
        this.userDetailsService = userDetailsService;
        this.userDetailsTransformer = userDetailsTransformer;
    }

    /**
     * Get user by user id endpoint.
     *
     * @param email email of the seached user.
     * @return User with the related email.
     */
    @GET
    @Path("/{userId}")
    @Produces(APPLICATION_JSON)
    public Response retrieveClubs(@Auth Principal principal, @PathParam("userId") String email) {
        LOGGER.info("Getting user details request");
        checkIfUserAuthenticated(principal, email);
        User user = userDetailsService.getUserDetailsByEmail(email);
        UserDetails userDetails = userDetailsTransformer.transform(user);
        return Response.ok().entity(userDetails).build();

    }

    private void checkIfUserAuthenticated(Principal principal, String email) {
        if (!principal.getName().equals(email)) {
            String exceptionMessage = "User not authenticated to get details. Actual user: " + principal.getName() + ", user to get details about: "
                    + email;
            LOGGER.warn(exceptionMessage);
            throw new AuthenticationFailedException(exceptionMessage, new AuthenticationException(exceptionMessage));
        }
    }

}
