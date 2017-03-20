package hu.terray.receipttovoucher.auth;

import com.google.inject.Singleton;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import io.dropwizard.auth.Authorizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * For authorizing user request.
 */
@Singleton
public class UserAuthorizer implements Authorizer<User> {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserAuthorizer.class);

    @Override
    public boolean authorize(User user, String role) {
        return user != null && user.getRole() != null && user.getRole().equalsIgnoreCase(role);
    }
}
