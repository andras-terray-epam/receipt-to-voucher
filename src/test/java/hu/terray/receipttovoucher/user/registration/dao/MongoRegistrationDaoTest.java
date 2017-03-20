package hu.terray.receipttovoucher.user.registration.dao;

import com.mongodb.DuplicateKeyException;
import hu.terray.receipttovoucher.common.exception.system.badrequest.RTVUserCreationFailedException;
import hu.terray.receipttovoucher.common.mongo.MongoDBCollectionProvider;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import hu.terray.receipttovoucher.user.registration.dao.transformer.UserTransformer;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mongojack.JacksonDBCollection;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Test for {@link MongoRegistrationDao}
 */
@RunWith(MockitoJUnitRunner.class)
public class MongoRegistrationDaoTest {

    @Mock
    private MongoDBCollectionProvider collectionProvider;

    @Mock
    private UserTransformer userTransformer;

    @Mock
    private JacksonDBCollection<User, String> userCollection;

    private RegistrationRequest registrationRequest;
    private User userToInsert = new User();

    @InjectMocks
    private MongoRegistrationDao underTest;

    @Before
    public void setUp() {
        given(collectionProvider.getWrappedCollection(MongoRegistrationDao.USER_COLLECTION_NAME, User.class, String.class))
                .willReturn(userCollection);
        given(userTransformer.transform(registrationRequest)).willReturn(userToInsert);
        underTest = new MongoRegistrationDao(collectionProvider, userTransformer);
        underTest.start();
    }

    @Test
    public void registerShouldInsertTransformedRequestIntoDBAndGivesBackId() throws Exception {
        // GIVEN
        String expectedId = "uuid";
        userToInsert.setUserId(expectedId);

        // WHEN
        RegistrationResponse response = underTest.register(registrationRequest);

        // THEN
        verify(userTransformer).transform(registrationRequest);
        verify(userCollection).insert(userToInsert);
        assertEquals(expectedId, response.getUserId());

    }

    @Test(expected = RTVUserCreationFailedException.class)
    public void registerShouldThrowCustomExceptionWhenDuplicatedKeyExceptionGot() throws Exception {
        // GIVEN
        given(userCollection.insert(userToInsert)).willThrow(DuplicateKeyException.class);

        // WHEN
        underTest.register(registrationRequest);

        // THEN custom exception

    }

}
