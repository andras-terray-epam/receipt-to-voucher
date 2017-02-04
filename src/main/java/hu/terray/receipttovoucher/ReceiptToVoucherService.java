package hu.terray.receipttovoucher;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by andrasterray on 2/4/17.
 */
public class ReceiptToVoucherService extends Application<AppConfiguration> {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReceiptToVoucherService.class);

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        GuiceBundle<AppConfiguration> guiceBundle = GuiceBundle.<AppConfiguration>newBuilder()
                .addModule(new ServerModule())
                .setConfigClass(AppConfiguration.class)
                .enableAutoConfig(getClass().getPackage().getName())
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    public static void main(final String[] args) throws Exception {
        new ReceiptToVoucherService().run(args);
    }

    @Override
    public void run(final AppConfiguration configuration, final Environment environment) throws Exception {
        /*LOGGER.info("Application name: {}", configuration.getAppName());

        //Adding real resource
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        //adding healthcheck
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);*/
    }
}
