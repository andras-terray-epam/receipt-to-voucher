package hu.terray.receipttovoucher.user.login.dao;

/**
 * Authentication related methods.
 */
public interface AuthenticationDao {
    boolean areLoginCredentialsValid(String email, String password);
}
