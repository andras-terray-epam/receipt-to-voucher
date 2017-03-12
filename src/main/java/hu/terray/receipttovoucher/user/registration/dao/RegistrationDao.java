package hu.terray.receipttovoucher.user.registration.dao;

import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationResponse;

/**
 * Registration dao.
 */
public interface RegistrationDao {
    /**
     * Register user.
     *
     * @param registrationRequest user registration request details.
     * @return id of the created user.
     */
    RegistrationResponse register(RegistrationRequest registrationRequest);
}
