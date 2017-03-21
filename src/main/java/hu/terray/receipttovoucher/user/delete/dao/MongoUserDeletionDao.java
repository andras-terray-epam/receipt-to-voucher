package hu.terray.receipttovoucher.user.delete.dao;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.common.exception.system.badrequest.AuthenticationFailedException;
import hu.terray.receipttovoucher.common.exception.system.badrequest.BadRequestException;
import hu.terray.receipttovoucher.common.mongo.MongoDBCollectionProvider;
import hu.terray.receipttovoucher.common.mongo.Repository;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

/**
 * Mongo version of user deletion dao.
 */
@Singleton
public class MongoUserDeletionDao implements Repository<User, String>, UserDeletionDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(MongoUserDeletionDao.class);

    private static final String USER_COLLECTION_NAME = "user";
    private static final String ADMIN_ROLE = "admin";

    private final MongoDBCollectionProvider collectionProvider;

    private JacksonDBCollection<User, String> userCollection;

    /**
     * Constructor with necessary dependencies.
     *
     * @param collectionProvider dependency.
     */
    @Inject
    public MongoUserDeletionDao(final MongoDBCollectionProvider collectionProvider) {
        this.collectionProvider = collectionProvider;
    }

    @Override
    public void start() {
        userCollection = collectionProvider.getWrappedCollection(USER_COLLECTION_NAME, User.class, String.class);
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = null;
        try {
            user = userCollection.findOne(DBQuery.is("email", email));
            preventDeletingAdminUser(user);
            userCollection.remove(DBQuery.is("email", email));
        } catch (final IllegalArgumentException e) {
            String exceptionMessage = "User cannot be found with this email: ";
            LOGGER.error(exceptionMessage + email);
            throw new BadRequestException(exceptionMessage, e);
        }
    }

    private void preventDeletingAdminUser(User user) {
        if (user.getRole() != null && ADMIN_ROLE.equals(user.getRole().toLowerCase())) {
            throw new AuthenticationFailedException("Permission denied. Not Admin user!");
        }
    }
}
