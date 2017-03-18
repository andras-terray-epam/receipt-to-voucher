package hu.terray.receipttovoucher.auth.token;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.AppConfiguration;
import hu.terray.receipttovoucher.common.exception.system.SystemException;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.UnsupportedEncodingException;

import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

/**
 * Token creation functionality.
 */
@Singleton
public class TokenFactory {

    public static final Logger LOGGER = LoggerFactory.getLogger(TokenFactory.class);

    private static final String SUBJECT = "";
    private static final int EXPIRATION_TIME_IN_MINUTES = 60;

    private final AppConfiguration configuration;

    @Inject
    public TokenFactory(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    public String createToken(String subject) {
        final JwtClaims claims = new JwtClaims();
        claims.setSubject(subject);
        claims.setExpirationTimeMinutesInTheFuture(EXPIRATION_TIME_IN_MINUTES);

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);

        try {
            jws.setKey(new HmacKey(configuration.getJwtTokenSecret()));
        } catch (UnsupportedEncodingException e) {
            String exceptionMessage = "Exception during JWT Token key set";
            LOGGER.error(exceptionMessage);
            throw new SystemException(exceptionMessage, e);
        }

        try {
            return jws.getCompactSerialization();
        } catch (JoseException e) {
            String exceptionMessage = "Exception during JWT token generation";
            LOGGER.error(exceptionMessage);
            throw new SystemException(exceptionMessage, e);
        }
    }

}
