package hu.terray.receipttovoucher.user.details.service;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.user.details.dao.UserDetailsDao;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;

import javax.inject.Singleton;

/**
 * User details service.
 */
@Singleton
public class UserDetailsService {
    private UserDetailsDao userDetailsDao;

    @Inject
    public UserDetailsService(UserDetailsDao userDetailsDao) {
        this.userDetailsDao = userDetailsDao;
    }

    public User getUserDetailsById(String userId) {
        return userDetailsDao.getUserDetailsById(userId);
    }
}