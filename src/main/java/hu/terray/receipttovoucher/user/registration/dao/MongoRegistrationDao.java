package hu.terray.receipttovoucher.user.registration.dao;

import javax.inject.Singleton;

import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import hu.terray.receipttovoucher.common.mongo.DefaultCollectionProvider;
import hu.terray.receipttovoucher.common.mongo.Repository;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import hu.terray.receipttovoucher.user.registration.dao.transformer.UserTransformer;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;

/**
 * Mongo db version of registration dao.
 */
@Singleton
public class MongoRegistrationDao implements Repository<User, String> {

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

    public boolean register(RegistrationRequest registrationRequest) {

        ObjectMapper om = new ObjectMapper();
        User userToInsert = userTransformer.transform(registrationRequest);

        WriteResult<User, String> result = userCollection.insert(userToInsert);

        return true;
    }
}
