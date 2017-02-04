package hu.terray.receipttovoucher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

/**
 * Created by andrasterray on 2/4/17.
 */
@Singleton
public class GuiceTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReceiptToVoucherService.class);

    public void logSomething() {
        LOGGER.info("It seems guice is working well :) ");

    }
}
