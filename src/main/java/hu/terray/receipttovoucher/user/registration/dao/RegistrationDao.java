package hu.terray.receipttovoucher.user.registration.dao;

import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationResponse;

/**
 * Registration dao.
 */
public interface RegistrationDao {
    RegistrationResponse register(RegistrationRequest registrationRequest);
}
