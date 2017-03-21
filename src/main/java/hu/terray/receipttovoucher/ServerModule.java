package hu.terray.receipttovoucher;

import com.google.inject.AbstractModule;
import hu.terray.receipttovoucher.common.uuid.DefaultUUIDGenerator;
import hu.terray.receipttovoucher.common.uuid.UUIDGenerator;
import hu.terray.receipttovoucher.user.delete.dao.MongoUserDeletionDao;
import hu.terray.receipttovoucher.user.delete.dao.UserDeletionDao;
import hu.terray.receipttovoucher.user.details.dao.MongoUserDetailsDao;
import hu.terray.receipttovoucher.user.details.dao.UserDetailsDao;
import hu.terray.receipttovoucher.user.login.dao.AuthenticationDao;
import hu.terray.receipttovoucher.user.login.dao.MongoAuthenticationDao;
import hu.terray.receipttovoucher.user.registration.dao.MongoRegistrationDao;
import hu.terray.receipttovoucher.user.registration.dao.RegistrationDao;

/**
 * {@link com.google.inject.Module} implementation for binding interfaces to related implementations.
 */
public class ServerModule extends AbstractModule {

    /**
     * Bind interfaces to releted implementations.
     */
    @Override
    protected void configure() {
        bind(UUIDGenerator.class).to(DefaultUUIDGenerator.class);
        bind(UserDetailsDao.class).to(MongoUserDetailsDao.class);
        bind(RegistrationDao.class).to(MongoRegistrationDao.class);
        bind(AuthenticationDao.class).to(MongoAuthenticationDao.class);
        bind(UserDeletionDao.class).to(MongoUserDeletionDao.class);
    }
}
