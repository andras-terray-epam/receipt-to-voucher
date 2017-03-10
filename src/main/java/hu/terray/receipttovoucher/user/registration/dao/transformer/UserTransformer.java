package hu.terray.receipttovoucher.user.registration.dao.transformer;

import javax.inject.Singleton;

import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;

@Singleton
public class UserTransformer {

    public User transform(RegistrationRequest registrationRequest) {
        User userToInsert = new User();
        userToInsert.setEmailAddress(registrationRequest.getEmailAddress());
        userToInsert.setFirstName(registrationRequest.getFirstName());
        userToInsert.setLastName(registrationRequest.getLastName());
        userToInsert.setPassword(registrationRequest.getPassword());
        return userToInsert;
    }
}
