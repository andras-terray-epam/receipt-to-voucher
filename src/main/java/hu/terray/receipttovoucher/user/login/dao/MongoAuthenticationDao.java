package hu.terray.receipttovoucher.user.login.dao;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.common.exception.system.badrequest.AuthenticationFailedException;
import hu.terray.receipttovoucher.common.mongo.MongoDBCollectionProvider;
import hu.terray.receipttovoucher.common.mongo.Repository;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import io.dropwizard.auth.AuthenticationException;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

/**
 * Mongo specific implementation of user authentication.
 */
@Singleton
public class MongoAuthenticationDao implements AuthenticationDao, Repository<User, String> {

    public static final Logger LOGGER = LoggerFactory.getLogger(MongoAuthenticationDao.class);

    private static final String USER_COLLECTION_NAME = "user";
    public static final String USER_CANNOT_BE_FOUND = "User cannot be found with the given email address in mongo db: ";

    private final MongoDBCollectionProvider collectionProvider;

    private JacksonDBCollection<User, String> userCollection;

    @Inject
    public MongoAuthenticationDao(MongoDBCollectionProvider collectionProvider) {
        this.collectionProvider = collectionProvider;
    }

    @Override
    public void start() {
        userCollection = collectionProvider.getWrappedCollection(USER_COLLECTION_NAME, User.class, String.class);
    }

    @Override
    public boolean areLoginCredentialsValid(String email, String password) {
        User user = getUserByEmail(email);
        checkIfUserFound(email, user);
        return password.equals(user.getPassword());
    }

    private User getUserByEmail(String email) {
        User user = null;
        try {
            user = userCollection.findOne(DBQuery.is("email", email));
        } catch (final IllegalArgumentException e) {
            String exceptionMessage = USER_CANNOT_BE_FOUND + email;
            LOGGER.warn(exceptionMessage, e);
            throw new AuthenticationFailedException(exceptionMessage, e);
        }
        return user;
    }

    private void checkIfUserFound(String email, User user) {
        if (user == null) {
            String exceptionMessage = USER_CANNOT_BE_FOUND + email;
            LOGGER.warn(exceptionMessage);
            throw new AuthenticationFailedException(exceptionMessage, new AuthenticationException(exceptionMessage));
        }
    }
}
