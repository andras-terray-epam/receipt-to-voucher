package hu.terray.receipttovoucher.user.details.dao;

import hu.terray.receipttovoucher.user.registration.dao.domain.User;

/**
 * User details dao.
 */
public interface UserDetailsDao {
    /**
     * Gives back user with the related userId.
     *
     * @param userId id of the user.
     * @return User with related id.
     */
    User getUserDetailsById(String userId);
}
