package hu.terray.receipttovoucher;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import hu.terray.receipttovoucher.common.uuid.DefaultUUIDGenerator;
import hu.terray.receipttovoucher.common.uuid.UUIDGenerator;
import hu.terray.receipttovoucher.user.details.dao.MongoUserDetailsDao;
import hu.terray.receipttovoucher.user.details.dao.UserDetailsDao;
import hu.terray.receipttovoucher.user.registration.dao.MongoRegistrationDao;
import hu.terray.receipttovoucher.user.registration.dao.RegistrationDao;

import javax.inject.Named;


/**
 * {@link com.google.inject.Module} implementation for binding interfaces to related classes.
 */
public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UUIDGenerator.class).to(DefaultUUIDGenerator.class);
        bind(UserDetailsDao.class).to(MongoUserDetailsDao.class);
        bind(RegistrationDao.class).to(MongoRegistrationDao.class);
    }

    @Provides
    @Named("template")
    public String provideTemplate(AppConfiguration appConfiguration) {
        return appConfiguration.getTemplate();
    }

    @Provides
    @Named("defaultName")
    public String provideDefaultName(AppConfiguration appConfiguration) {
        return appConfiguration.getDefaultName();
    }
}
