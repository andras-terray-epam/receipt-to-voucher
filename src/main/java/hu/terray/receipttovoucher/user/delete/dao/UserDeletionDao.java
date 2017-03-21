package hu.terray.receipttovoucher.user.delete.dao;

/**
 * User deletion dao.
 */
public interface UserDeletionDao {
    /**
     * Delete the user based on the email address.
     *
     * @param email email of the user.
     */
    void deleteUserByEmail(String email);
}
