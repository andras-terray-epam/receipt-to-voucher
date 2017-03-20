package hu.terray.receipttovoucher.auth;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import hu.terray.receipttovoucher.common.exception.system.badrequest.AuthenticationFailedException;
import hu.terray.receipttovoucher.user.details.service.UserDetailsService;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import io.dropwizard.auth.Authenticator;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Methods used for authenticating.
 */
@Singleton
public class UserAuthenticator implements Authenticator<JwtContext, User> {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticator.class);

    private final UserDetailsService userDetailsService;

    /**
     * Constructor with necessary dependencies.
     *
     * @param userDetailsService     dependency.
     */
    @Inject
    public UserAuthenticator(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Optional<User> authenticate(JwtContext context) {
        Optional<User> optionalUser = Optional.empty();
        try {
            checkIfTokenExpired(context);
            optionalUser = getTokenRelatedUser(context);
        } catch (MalformedClaimException e) {
            handleMalformedClaimException(e);
        } catch (InvalidJwtException e) {
            handleInvalidTokenException(e);
        }
        return optionalUser;
    }

    private void checkIfTokenExpired(JwtContext context) throws MalformedClaimException, InvalidJwtException {
        NumericDate expirationTime = context.getJwtClaims().getExpirationTime();
        if (expirationTime.isBefore(NumericDate.now())) {
            throw new InvalidJwtException("expired at " + expirationTime); // or whatever
        }
    }

    private Optional<User> getTokenRelatedUser(JwtContext context) throws MalformedClaimException {
        final String subject = context.getJwtClaims().getSubject();
        User user = userDetailsService.getUserDetailsByEmail(subject);
        return Optional.of(user);
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
