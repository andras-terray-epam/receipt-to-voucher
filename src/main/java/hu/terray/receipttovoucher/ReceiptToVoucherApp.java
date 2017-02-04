package hu.terray.receipttovoucher;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by andrasterray on 2/4/17.
 */
public class ReceiptToVoucherApp extends Application<AppConfiguration> {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReceiptToVoucherApp.class);

    public static void main(final String[] args) throws Exception {
        new ReceiptToVoucherApp().run(args);
    }

    @Override
    public void run(final AppConfiguration configuration, final Environment environment) throws Exception {
        LOGGER.info("Application name: {}", configuration.getAppName());
    }
}
