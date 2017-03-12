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

    /**
     * Constructor with necessary dependencies.
     *
     * @param userDetailsDao dependency.
     */
    @Inject
    public UserDetailsService(UserDetailsDao userDetailsDao) {
        this.userDetailsDao = userDetailsDao;
    }

    /**
     * Get user based on its id.
     *
     * @param userId id of the user.
     * @return User related to the id.
     */
    public User getUserDetailsById(String userId) {
        return userDetailsDao.getUserDetailsById(userId);
    }
}
