package hu.terray.receipttovoucher.user.details.resource;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.user.details.resource.domain.UserDetails;
import hu.terray.receipttovoucher.user.details.resource.transformer.UserDetailsTransformer;
import hu.terray.receipttovoucher.user.details.service.UserDetailsService;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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
    public UserDetailsResource(final UserDetailsService userDetailsService,
            final UserDetailsTransformer userDetailsTransformer) {
        this.userDetailsService = userDetailsService;
        this.userDetailsTransformer = userDetailsTransformer;
    }

    /**
     * Get user by user id endpoint.
     *
     * @param userId id of the seached user.
     * @return User with the related id.
     */
    @GET
    @Path("/{userId}")
    @Produces(APPLICATION_JSON)
    public Response retrieveClubs(@PathParam("userId") String userId) {
        LOGGER.info("Getting user details request");
        User user = userDetailsService.getUserDetailsById(userId);
        UserDetails userDetails = userDetailsTransformer.transform(user);
        return Response.ok().entity(userDetails).build();
    }

}
