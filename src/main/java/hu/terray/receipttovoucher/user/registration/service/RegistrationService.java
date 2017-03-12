package hu.terray.receipttovoucher.user.registration.service;

import com.google.inject.Inject;
import hu.terray.receipttovoucher.user.registration.dao.MongoRegistrationDao;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationResponse;

import javax.inject.Singleton;

/**
 * Registration service.
 */
@Singleton
public class RegistrationService {

    private MongoRegistrationDao mongoRegistrationDao;

    @Inject
    public RegistrationService(MongoRegistrationDao mongoRegistrationDao) {
        this.mongoRegistrationDao = mongoRegistrationDao;
    }

    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        return mongoRegistrationDao.register(registrationRequest);
    }
}
