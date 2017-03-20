package hu.terray.receipttovoucher;

import com.github.toastshaman.dropwizard.auth.jwt.JwtAuthFilter;
import com.google.inject.Injector;
import com.hubspot.dropwizard.guice.GuiceBundle;
import hu.terray.receipttovoucher.auth.UserAuthenticator;
import hu.terray.receipttovoucher.auth.UserAuthorizer;
import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;

/**
 * Created by andrasterray on 2/4/17.
 */
public class ReceiptToVoucherApplication extends Application<AppConfiguration> {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReceiptToVoucherApplication.class);

    private Injector injector;

    /**
     * Entry point of the Receipt to voucher application.
     *
     * @param args application arguments.
     * @throws Exception exceptions can be thrown.
     */
    // CHECKSTYLE:OFF
    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    public static void main(final String[] args) throws Exception {
        new ReceiptToVoucherApplication().run(args);
    }
    // CHECKSTYLE:ON

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        GuiceBundle<AppConfiguration> guiceBundle = GuiceBundle.<AppConfiguration>newBuilder()
                .addModule(new ServerModule())
                .setConfigClass(AppConfiguration.class)
                .enableAutoConfig(getClass().getPackage().getName())
                .build();
        bootstrap.addBundle(guiceBundle);
        injector = guiceBundle.getInjector();
    }

    // CHECKSTYLE:OFF
    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    @Override
    public void run(final AppConfiguration configuration, final Environment environment) throws Exception {
        final byte[] key = configuration.getJwtTokenSecret();

        // allow some leeway in validating time based claims to account for clock skew
        final JwtConsumer consumer = new JwtConsumerBuilder().setAllowedClockSkewInSeconds(30)
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(new HmacKey(key)) // verify the signature with the public key
                .setRelaxVerificationKeyValidation() // relaxes key length requirement
                .build(); // create the JwtConsumer instance

        UserAuthenticator userAuthenticator = injector.getInstance(UserAuthenticator.class);
        UserAuthorizer userAuthorizer = injector.getInstance(UserAuthorizer.class);
        environment.jersey().register(new AuthDynamicFeature(new JwtAuthFilter.Builder<User>().setJwtConsumer(consumer)
                .setRealm("realm")
                .setPrefix("Bearer")
                .setAuthenticator(userAuthenticator)
                .setAuthorizer(userAuthorizer)
                .buildAuthFilter()));

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Principal.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
    }
    // CHECKSTYLE:ON
}
