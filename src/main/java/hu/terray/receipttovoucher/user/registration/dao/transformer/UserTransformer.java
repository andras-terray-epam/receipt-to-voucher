package hu.terray.receipttovoucher.user.registration.dao.transformer;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.common.uuid.UUIDGenerator;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;

import javax.inject.Singleton;

/**
 * Transforming from service to dao level.
 */
@Singleton
public class UserTransformer {

    private UUIDGenerator uuidGenerator;

    @Inject
    public UserTransformer(UUIDGenerator uuidGenerator) {
        this.uuidGenerator = uuidGenerator;
    }

    public User transform(RegistrationRequest registrationRequest) {
        User userToInsert = new User();
        userToInsert.setUserId(uuidGenerator.createRandomUUID());
        userToInsert.setEmail(registrationRequest.getEmail());
        userToInsert.setFirstName(registrationRequest.getFirstName());
        userToInsert.setLastName(registrationRequest.getLastName());
        userToInsert.setPassword(registrationRequest.getPassword());
        return userToInsert;
    }
}
