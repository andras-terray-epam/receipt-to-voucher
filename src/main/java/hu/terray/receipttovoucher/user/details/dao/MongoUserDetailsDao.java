package hu.terray.receipttovoucher.user.details.dao;

import com.google.inject.Inject;
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
 * Mongo version of user Details Dao.
 */
@Singleton
public class MongoUserDetailsDao implements Repository<User, String>, UserDetailsDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(MongoUserDetailsDao.class);

    private static final String USER_COLLECTION_NAME = "user";

    private final MongoDBCollectionProvider collectionProvider;

    private JacksonDBCollection<User, String> userCollection;

    /**
     * Constructor with necessary dependencies.
     *
     * @param collectionProvider dependency.
     */
    @Inject
    public MongoUserDetailsDao(final MongoDBCollectionProvider collectionProvider) {
        this.collectionProvider = collectionProvider;
    }

    @Override
    public void start() {
        userCollection = collectionProvider.getWrappedCollection(USER_COLLECTION_NAME, User.class, String.class);
    }

    @Override
    public User getUserDetailsByEmail(String email) {
        User user = null;
        try {
            user = userCollection.findOne(DBQuery.is("email", email));
        } catch (final IllegalArgumentException e) {
            String exceptionMessage = "User cannot be found with this email: ";
            LOGGER.error(exceptionMessage + email);
            throw new BadRequestException(exceptionMessage, e);
        }
        return user;
    }
}
