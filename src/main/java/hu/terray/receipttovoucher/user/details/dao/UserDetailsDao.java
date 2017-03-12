package hu.terray.receipttovoucher.user.details.dao;

import hu.terray.receipttovoucher.user.registration.dao.domain.User;

/**
 * User details dao.
 */
public interface UserDetailsDao {
    User getUserDetailsById(String userId);
}
