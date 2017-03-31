package hu.terray.receipttovoucher.user.login.service;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.auth.token.TokenFactory;
import hu.terray.receipttovoucher.common.exception.system.badrequest.AuthenticationFailedException;
import hu.terray.receipttovoucher.user.login.dao.AuthenticationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

/**
 * Calls authentication dao with request info.
 */
@Singleton
public class AuthenticationService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    private final TokenFactory tokenFactory;
    private final AuthenticationDao authenticationDao;

    @Inject
    public AuthenticationService(TokenFactory tokenFactory, final AuthenticationDao authenticationDao) {
        this.tokenFactory = tokenFactory;
        this.authenticationDao = authenticationDao;
    }

    /**
     * Calls authentication dao with credentials.
     *
     * @param email email
     * @param password password
     * @return if credentials are valid
     */
    public String areLoginCredentialsValid(String email, String password) {
        if (!authenticationDao.areLoginCredentialsValid(email, password)) {
            String exceptionMessage = "User credentials are not valid for email: " + email;
            LOGGER.warn(exceptionMessage);
            throw new AuthenticationFailedException(exceptionMessage);
        } else {
            return tokenFactory.createToken(email);
        }
    }
}
