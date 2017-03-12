package hu.terray.receipttovoucher.user.registration.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.mongodb.DuplicateKeyException;
import hu.terray.receipttovoucher.common.exception.RTVUserCreationFailedException;
import hu.terray.receipttovoucher.common.mongo.DefaultCollectionProvider;
import hu.terray.receipttovoucher.common.mongo.Repository;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import hu.terray.receipttovoucher.user.registration.dao.transformer.UserTransformer;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationResponse;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

/**
 * Mongo db version of registration dao.
 */
@Singleton
public class MongoRegistrationDao implements Repository<User, String> {

    public static final Logger LOGGER = LoggerFactory.getLogger(MongoRegistrationDao.class);

    private static final String USER_COLLECTION_NAME = "user";

    private final DefaultCollectionProvider collectionProvider;
    private final UserTransformer userTransformer;

    private JacksonDBCollection<User, String> userCollection;

    @Inject
    public MongoRegistrationDao(final DefaultCollectionProvider collectionProvider, final UserTransformer userTransformer) {
        this.collectionProvider = collectionProvider;
        this.userTransformer = userTransformer;
    }

    @Override
    public void start() {
        userCollection = collectionProvider.getWrappedCollection(USER_COLLECTION_NAME, User.class, String.class);
    }

    public RegistrationResponse register(RegistrationRequest registrationRequest) {

        ObjectMapper om = new ObjectMapper();
        User userToInsert = userTransformer.transform(registrationRequest);


        WriteResult<User, String> result = null;
        try {
            result = userCollection.insert(userToInsert);
        } catch (DuplicateKeyException e) {
            LOGGER.error("User with this email address already exist in DB: ", e);
            throw new RTVUserCreationFailedException("User creation was not successful because the email already exist in DB", e);
        }

        return new RegistrationResponse(userToInsert.getUserId());
    }
}
