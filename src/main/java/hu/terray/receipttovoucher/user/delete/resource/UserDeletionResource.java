package hu.terray.receipttovoucher.user.delete.resource;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.user.delete.service.UserDeletionService;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import io.dropwizard.auth.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
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
public class UserDeletionResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDeletionResource.class);

    private final UserDeletionService userDeletionService;

    /**
     * Constructor with necessary dependencies.
     *
     * @param userDeletionService     dependency.
     */
    @Inject
    public UserDeletionResource(final UserDeletionService userDeletionService) {
        this.userDeletionService = userDeletionService;
    }

    /**
     * Delete user by email endpoint.
     *
     * @param user user to authenticate on.
     * @param email email of the user to be deleted.
     * @return OK if found and deleted.
     */
    @DELETE
    @Path("/{email}/delete")
    @RolesAllowed("admin")
    @Produces(APPLICATION_JSON)
    public Response retrieveClubs(@Auth User user, @PathParam("email") String email) {
        LOGGER.info("Getting user deletion request");
        userDeletionService.deleteUserByEmail(email);
        return Response.ok().entity(email).build();

    }

}
