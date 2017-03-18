package hu.terray.receipttovoucher.auth;

import com.google.inject.Singleton;
import hu.terray.receipttovoucher.common.exception.system.badrequest.AuthenticationFailedException;
import hu.terray.receipttovoucher.user.details.service.UserDetailsService;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Auth util.
 */
@Singleton
public class AuthUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticator.class);

    private static final Map<String, String> USER_AND_ROLE_MAP;
    private static final String ADMIN_ROLE = "admin";
    private static final String ADMIN_USER = "admin@admin.com";
    private static final String SUPER_ADMIN_ROLE = "superAdmin";
    private static final String SUPER_ADMIN_USER = "superAdmin";

    private final UserDetailsService userDetailsService;

    static {
        USER_AND_ROLE_MAP = new HashMap<String, String>();
        USER_AND_ROLE_MAP.put("admin", "admin@admin.com");
        USER_AND_ROLE_MAP.put("superAdmin", "superAdmin@superadmin.com");
    }

    public AuthUtil(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public Authenticator<JwtContext, User> getJWTAuthenticator() {
        return context -> {
            Optional<User> optionalUser = Optional.empty();
            try {
                checkIfTokenExpired(context);
                optionalUser = getTokenRelatedUser(context);
            } catch (MalformedClaimException e) {
                handleMalformedClaimException(e);
            } catch (InvalidJwtException e) {
                handleInvalidTokenException(e);
            }
            LOGGER.error(optionalUser.get().getEmail() + "***************" + optionalUser.get().getRole());
            return optionalUser;
        };
    }

    public Authorizer<User> getAuthorizer() {
        return (user, role) -> {
            // iterate and check
            // return user != null && validUser.equals(user.getName()) && validRole.equals(role);
            return true;
        };
    }

    private void checkIfTokenExpired(JwtContext context) throws MalformedClaimException, InvalidJwtException {
        NumericDate expirationTime = context.getJwtClaims().getExpirationTime();
        if (expirationTime.isBefore(NumericDate.now())) {
            throw new InvalidJwtException("expired at " + expirationTime); // or whatever
        }
    }

    private Optional<User> getTokenRelatedUser(JwtContext context) throws MalformedClaimException {
        final String subject = context.getJwtClaims().getSubject();
        return Optional.of(userDetailsService.getUserDetailsByEmail(subject));
    }

    private void handleMalformedClaimException(MalformedClaimException e) {
        String exceptionMessage = "Claim is malformed";
        LOGGER.warn(exceptionMessage, e);
        throw new AuthenticationFailedException(exceptionMessage, e);
    }

    private void handleInvalidTokenException(InvalidJwtException e) {
        String exceptionMessage = "Invalid token";
        LOGGER.warn(exceptionMessage, e);
        throw new AuthenticationFailedException(exceptionMessage, e);
    }
}
