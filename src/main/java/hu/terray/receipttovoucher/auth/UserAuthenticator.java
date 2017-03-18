package hu.terray.receipttovoucher.auth;

import hu.terray.receipttovoucher.common.exception.system.badrequest.AuthenticationFailedException;
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
public class UserAuthenticator implements Authenticator<JwtContext, User> {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticator.class);

    @Override
    public Optional<User> authenticate(JwtContext context) {
        // Provide your own implementation to lookup users based on the principal attribute in the
        // JWT Token. E.g.: lookup users from a database etc.
        // This method will be called once the token's signature has been verified

        // In case you want to verify different parts of the token you can do that here.
        // E.g.: Verifying that the provided token has not expired.

        // All JsonWebTokenExceptions will result in a 401 Unauthorized response.

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
        User user = new User();
        user.setEmail(subject);
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
