package hu.terray.receipttovoucher.user.details.dao;

import hu.terray.receipttovoucher.user.registration.dao.domain.User;

/**
 * User details dao.
 */
public interface UserDetailsDao {
    /**
     * Gives back user with the related email.
     *
     * @param email email of the user.
     * @return User with related email.
     */
    User getUserDetailsByEmail(String email);
}
