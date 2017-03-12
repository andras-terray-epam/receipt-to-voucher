package hu.terray.receipttovoucher.user.registration.dao;

import com.google.inject.Inject;
import com.mongodb.DuplicateKeyException;
import hu.terray.receipttovoucher.common.exception.RTVUserCreationFailedException;
import hu.terray.receipttovoucher.common.mongo.MongoDBCollectionProvider;
import hu.terray.receipttovoucher.common.mongo.Repository;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import hu.terray.receipttovoucher.user.registration.dao.transformer.UserTransformer;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationResponse;
import org.mongojack.JacksonDBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

/**
 * Mongo db version of registration dao.
 */
@Singleton
public class MongoRegistrationDao implements Repository<User, String>, RegistrationDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(MongoRegistrationDao.class);

    private static final String USER_COLLECTION_NAME = "user";

    private final MongoDBCollectionProvider collectionProvider;
    private final UserTransformer userTransformer;

    private JacksonDBCollection<User, String> userCollection;

    /**
     * Constructor with necessary dependencies.
     *
     * @param collectionProvider dependency.
     * @param userTransformer    dependency.
     */
    @Inject
    public MongoRegistrationDao(final MongoDBCollectionProvider collectionProvider,
            final UserTransformer userTransformer) {
        this.collectionProvider = collectionProvider;
        this.userTransformer = userTransformer;
    }

    @Override
    public void start() {
        userCollection = collectionProvider.getWrappedCollection(USER_COLLECTION_NAME, User.class, String.class);
    }

    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest) {

        User userToInsert = userTransformer.transform(registrationRequest);

        try {
            userCollection.insert(userToInsert);
        } catch (DuplicateKeyException e) {
            LOGGER.error("User with this email address already exist in DB: ", e);
            throw new RTVUserCreationFailedException(
                    "User creation was not successful because the email already exist in DB", e);
        }

        return new RegistrationResponse(userToInsert.getUserId());
    }
}
