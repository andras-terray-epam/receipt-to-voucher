package hu.terray.receipttovoucher.user.details.resource.transformer;

import hu.terray.receipttovoucher.user.details.resource.domain.UserDetails;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;

import javax.inject.Singleton;

/**
 * Transforming from service to resource level.
 */
@Singleton
public class UserDetailsTransformer {
    public UserDetails transform(User user) {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(user.getUserId());
        userDetails.setEmail(user.getEmail());
        userDetails.setFirstName(user.getFirstName());
        userDetails.setLastName(user.getLastName());
        return userDetails;
    }
}
