package hu.terray.receipttovoucher.user.delete.service;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.user.delete.dao.UserDeletionDao;

import javax.inject.Singleton;

/**
 * User details service.
 */
@Singleton
public class UserDeletionService {
    private UserDeletionDao userDeletionDao;

    /**
     * Constructor with necessary dependencies.
     *
     * @param userDeletionDao     dependency.
     */
    @Inject
    public UserDeletionService(UserDeletionDao userDeletionDao) {
        this.userDeletionDao = userDeletionDao;
    }

    /**
     * Get user based on its id.
     *
     * @param email email of the user to be deleted.
     */
    public void deleteUserByEmail(String email) {
        userDeletionDao.deleteUserByEmail(email);
    }
}
