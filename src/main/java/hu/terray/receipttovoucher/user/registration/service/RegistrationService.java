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

    /**
     * Constructor with necessary dependencies.
     *
     * @param mongoRegistrationDao dependency.
     */
    @Inject
    public RegistrationService(MongoRegistrationDao mongoRegistrationDao) {
        this.mongoRegistrationDao = mongoRegistrationDao;
    }

    /**
     * Call dao implementation to create user based on the registration request.
     *
     * @param registrationRequest Registration related info.
     * @return Id of the registered user.
     */
    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        return mongoRegistrationDao.register(registrationRequest);
    }
}
